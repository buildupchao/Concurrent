package com.buildupchao.concurrent.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * @author buildupchao
 * @date 2020/01/08 20:24
 * @since JDK 1.8
 */
public class MyVerticleApplication extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx.createHttpServer()
                .requestHandler(r -> {
                    r.response().end("<h1>Hello, this is my first Vert.x 3 application</h1>");
                })
                .listen(8080, result -> {
                    if (result.succeeded()) {
                        startFuture.complete();
                    } else {
                        startFuture.fail(result.cause());
                    }
                });
    }
}
