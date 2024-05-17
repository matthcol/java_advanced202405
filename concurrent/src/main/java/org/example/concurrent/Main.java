package org.example.concurrent;

import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static void demoThreadPool(){
        try (ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4)) {
            executor.execute(() -> {
                long x = 0;
                for (long i=0; i<10_000_000_000L; i++ ) x += 3;
                System.out.println("Job result: " + x);
            });
            System.out.println("Job started");
        } // close => shutdown
    }

    static void demoForkJoinPool1(){
        try (ForkJoinPool executor = new ForkJoinPool(4)) {
            ForkJoinTask<Double> task = ForkJoinTask.adapt(MathUtils::piMonteCarlo);
            executor.execute(task);
            System.out.println("Job started");
            System.out.println("Finished: " + task.isDone());
            double pi = task.join();
            System.out.println("Finished: " + task.isDone());
            System.out.println("Result: " + pi);
        } // close => shutdown

    }

    static void computeParallelPi(int nJob, int nThread){
        // forkjoinpool of nThread
        Callable<PairDouble> computePiFunction = MathUtils::piMonteCarloCount;
        try (ForkJoinPool executor = new ForkJoinPool(nThread)) {
            // execute nJob piMonteCarloCount
            var tasks = IntStream.range(0,nJob)
                    .mapToObj(i -> executor.submit(ForkJoinTask.adapt(computePiFunction)))
                    .toList();
            System.out.print("Started tasks, finished states: " +
                    tasks.stream()
                            .map(task -> "" + task.isDone())
                            .collect(Collectors.joining(", "))
            );
            System.out.println();

            // collect result
            var counters = tasks.stream()
                    .map(ForkJoinTask::join)
                    .peek(System.out::println)
                    .reduce(
                            PairDouble.of(0.0, 0.0),
                            (p1, p2) -> PairDouble.of(
                                    p1.getFirst() + p2.getFirst(),
                                    p1.getSecond() + p2.getSecond()
                            )
                    );
            System.out.println(counters);
            double pi = 4.0 * counters.getFirst() / counters.getSecond();
            System.out.println("Pi = " + pi);
        }
        // sum counters and deduce pi value
    }

    public static void commputeParalellStreamPiCommonPool(int nJob, long nIteration){
        var counters = IntStream.range(0, nJob)
                .parallel()
                .mapToObj(i -> MathUtils.piMonteCarloCount(nIteration))
                .peek(System.out::println)
                .reduce(
                        PairDouble.of(0.0, 0.0),
                        (p1, p2) -> PairDouble.of(
                                p1.getFirst() + p2.getFirst(),
                                p1.getSecond() + p2.getSecond()
                        )
                );
        System.out.println(counters);
        double pi = 4.0 * counters.getFirst() / counters.getSecond();
        System.out.println("Pi = " + pi);
    }

    public static void commputeParalellStreamPi(int nJob, int nThread, long nIteration){
        try (ForkJoinPool executor = new ForkJoinPool(nThread)) {
            var task = executor.submit(() ->
                IntStream.range(0, nJob)
                    .parallel()
                    .mapToObj(i -> MathUtils.piMonteCarloCount(nIteration))
                    .peek(System.out::println)
                    .reduce(
                            PairDouble.of(0.0, 0.0),
                            (p1, p2) -> PairDouble.of(
                                    p1.getFirst() + p2.getFirst(),
                                    p1.getSecond() + p2.getSecond()
                            )
                    ));
            var counters = task.join();
            System.out.println(counters);
            double pi = 4.0 * counters.getFirst() / counters.getSecond();
            System.out.println("Pi = " + pi);
        }
    }

    public static void demoConcurrentBuffer() {
        ConcurrentBuffer buffer  = new ConcurrentBuffer(10);
        Productor productor = new Productor(30, 1, 2, buffer);
        Consumer consumer = new Consumer(30, 2, 0, buffer);
        System.out.println(buffer);
        try (ForkJoinPool executor = new ForkJoinPool(2)){
            executor.execute(productor::product);
            executor.execute(consumer::consume);
        }
    }

    public static void main(String[] args) {
        // main thread
        // demoThreadPool();
        // demoForkJoinPool1();

        // launch n monte carlo in //
        // computeParallelPi(12, 4);

        // commputeParalellStreamPi(12, 4, 100_000_000L);
        demoConcurrentBuffer();
    }
}
