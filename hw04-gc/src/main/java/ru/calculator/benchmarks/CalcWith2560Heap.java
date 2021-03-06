package ru.calculator.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.calculator.CalcDemo;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class CalcWith2560Heap {

    @Benchmark
    public void doCalc() {
        CalcDemo.main(new String[]{});
    }

    // Gives result of: 20495.964 ± 5310.966  ms/op
    // Gives result of: 2669.815 ± 205.122  ms/op
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + CalcWith2560Heap.class.getSimpleName() + ".*")
                .warmupIterations(3)
                .measurementIterations(5)
                .forks(2)
                .jvmArgs("-Xmx2560m")
                .build();

        new Runner(opt).run();
    }
}
