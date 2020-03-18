package com.buildupchao.concurrent.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * <p>
 *     Vertx web application
 *
 * @author buildupchao
 * @date 2020/01/08 21:26
 * @since JDK 1.8
 */
public class MyVerticleWebApplication extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        // create a router object
        Router router = Router.router(vertx);

        // Bind "/" to our hello message - so we are still compatible.
        router.route("/")
                .handler(routingContext -> {
                    HttpServerResponse response = routingContext.response();
                    response.putHeader("Content-Type", "text/html")
                            .end("<h1>Hello, this is my first Vert.x 3 application</h1>");
                });

        // server static resources from the /assets directory
        router.route("/assets/*")
                .handler(StaticHandler.create("assets"));

        // create the HTTP server and pass the "accept" method to the request handler/
        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(config().getInteger("http.port", 8080), result -> {
                   if (result.succeeded()) {
                       startFuture.complete();
                   } else {
                       startFuture.fail(result.cause());
                   }
                });
    }
}
