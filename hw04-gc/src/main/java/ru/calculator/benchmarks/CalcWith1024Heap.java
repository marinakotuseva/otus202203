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
public class CalcWith1024Heap {

    @Benchmark
    public void doCalc() {
        CalcDemo.main(new String[]{});
    }

    // Gives result of: 23491.491 ± 5581.891  ms/op
    // Gives result of: 3921.402 ± 1732.230  ms/op
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + CalcWith1024Heap.class.getSimpleName() + ".*")
                .warmupIterations(3)
                .measurementIterations(5)
                .forks(2)
                .jvmArgs("-Xmx1024m")
                .build();

        new Runner(opt).run();
    }
}
