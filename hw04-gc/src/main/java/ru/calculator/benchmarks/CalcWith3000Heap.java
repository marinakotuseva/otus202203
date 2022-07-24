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
public class CalcWith3000Heap {

    @Benchmark
    public void doCalc() {
        CalcDemo.main(new String[]{});
    }

    // Gives result of: 17033.201 ± 2736.214  ms/op
    // Gives result of: 2758.120 ± 262.718  ms/op
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + CalcWith3000Heap.class.getSimpleName() + ".*")
                .warmupIterations(3)
                .measurementIterations(5)
                .forks(2)
                .jvmArgs("-Xmx3000m")
                .build();

        new Runner(opt).run();
    }
}
