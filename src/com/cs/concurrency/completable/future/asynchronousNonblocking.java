package com.cs.concurrency.completable.future;


import org.junit.Test;

import java.sql.Time;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.*;

public class asynchronousNonblocking {

    /**
     * 在get的时候会阻塞式的获取结果
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> stringFuture = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "async thread";
            }
        });
        try {
            Thread.sleep(1000);
            System.out.println("main thread");
            System.out.println(stringFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //以下两个介绍runAsync和supplyAsync的用法
    /**
     * 无返回值
     * @throws Exception
     */
    @Test
    public void testRunAsync() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
           try {
               TimeUnit.SECONDS.sleep(1);
           }catch (Exception ignored){
               System.out.println("忽略");
           }
        });
        future.get();
    }

    /**
     * 有返回值
     * @throws Exception
     */
    @Test
    public void testSupplyAsync() throws Exception{
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("run end ...");
            return System.currentTimeMillis();
        });
        Long time = future.get();
        System.out.println(time);
    }

    /**
     * 当CompletableFuture的计算结果完成,或者抛出异常的时候,可以执行特定的Action.
     * whenComplete:是执行当前任务的线程执行继续执行whenComplete的任务.
     * whenCompleteAsync:是执行把whenCompleteAsync这个任务继续提交给线程池来进行执行
     * exceptionally:执行出现异常时，走这个方法
     * @throws Exception
     */
    @Test
    public void testWhenComplete() throws Exception{
        CompletableFuture.runAsync(() -> {
           try {
               TimeUnit.SECONDS.sleep(1);
           }catch (InterruptedException e){
               System.out.println(e);
           }
            System.out.println("运行结束");
        }).whenComplete((t,ation) -> {
            System.out.println("执行完成");
        }).exceptionally(t -> {
            System.out.println("出现异常:" + t);
            return null;
        });
        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * 当一个线程依赖另一个线程时，可以使用thenApply方法来把这两个线程串行化
     * 第二个任务依赖第一个任务的结果
     * @throws Exception
     */
    @Test
    public void testThenApply() throws Exception{
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            long result = new Random().nextInt();
            System.out.println("result:" + result);
            return result;
        }).thenApply(t -> {
            long result = t * 5;
            System.out.println("result2:" + result);
            return result;
        });
        Long result = future.get();
        System.out.println(result);
    }

    /**
     * thenCompose 方法允许你对两个 CompletableFuture 进行流水线操作，
     * 第一个操作完成时，将其结果作为参数传递给第二个操作。
     * @throws Exception
     */
     @Test
    public void testThenCompose() throws Exception{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
           int t = new Random().nextInt();
            System.out.println(t);
            return t;
        }).thenCompose(param -> {
            return CompletableFuture.supplyAsync(() -> {
                int t = param * 2;
                System.out.println("t2 = " + t);
                return t;
            });
        });
    }

    /**
     * handle 是执行任务完成时对结果的处理。
     * handle 方法和 thenApply 方法处理方式基本一样。
     * 不同的是 handle 是在任务完成后再执行，还可以处理异常的任务。
     * thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法
     * @throws Exception
     */
    @Test
    public void testHandle() throws Exception{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
           int i = 10 / 0;
           return i;
        }).handle((p,t) -> {
            int result = -1;
            if (Objects.isNull(t)){
                return result = p * 2;
            }else {
                System.out.println(t);
            }
            return result;
        });
        System.out.println(future.get());
    }

    /**
     * 接收任务的处理结果，并消费处理，无返回结果。
     *
     * @throws Exception
     */
    @Test
    public void testThenAccept() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            return new Random().nextInt();
        }).thenAccept(num -> {
            System.out.println(num);
        });
        System.out.println(future.get());
    }

    /**
     * 该方法同 thenAccept 方法类似。不同的是上个任务处理完成后，并不会把计算的结果传给 thenRun 方法。
     * 只是处理玩任务后，执行 thenRun 的后续操作。
     *
     * @throws Exception
     */
    @Test
    public void testThenRun() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
           return new Random().nextInt();
        }).thenRun(() -> {
            System.out.println("进入了thenRun");
        });
        System.out.println(future.get());
    }

    /**
     * thenCombine 会把 两个 CompletableFuture 的任务都执行完成后
     * 把两个任务的结果一块交给 thenCombine 来处理。
     * 有返回值
     * @throws Exception
     */
    @Test
    public void testThenCombine() throws Exception{
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
           return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
           return "world";
        }),(t1,t2) -> {
            return t1 + " " + t2;
        });
        System.out.println(future.get());
    }

    /**
     * 当两个 CompletableFuture 都执行完成后
     * 把结果一块交给thenAcceptBoth来进行消耗
     * 无返回值
     * @throws Exception
     */
    @Test
    public void testThenAcceptBoth() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
           return "hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            return "world";
        }),(t1,t2) -> {
            System.out.println(t1 + " " + t2);
        });
        System.out.println(future.get());
    }


    /**
     * 两个CompletableFuture，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作
     * 有返回值
     * @throws Exception
     */
    @Test
    public void testApplyToEither() throws Exception{
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
           try {
               Thread.sleep(2);
           }catch (InterruptedException e){
               System.out.println(e);
           }
           return "hello";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            return "world";
        }),(t) -> {
            return t;
        });
        System.out.println(future.get());
    }

    /**
     * 两个CompletableFuture，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的消耗操作。
     * 无返回值
     * @throws Exception
     */
    @Test
    public void testAcceptEither() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            return "hello";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            return "world";
        }), t1 -> {
            System.out.println(t1);
        });
        System.out.println(future.get());
    }

    /**
     * 两个CompletableFuture，任何一个完成了都会执行下一步的操作
     *
     * @throws Exception
     */
    @Test
    public void testRunAfterEither() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
           return "hello";
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            return "world";
        }),() -> {
            System.out.println("执行完了");
        });
        System.out.println(future.get());
    }
    /**
     * 两个CompletableFuture，都完成了计算才会执行下一步的操作
     *
     * @throws Exception
     */
    @Test
    public void testRunAfterBoth() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            return "hello";
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            return "world";
        }), () -> {
            System.out.println("执行完了");
        });
        System.out.println(future.get());
    }
}
