package com.buildupchao.concurrent.vertx;


import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * vertx使用json文件作为配置文件
 *
 * @author buildupchao
 * @date 2020/01/08 20:29
 * @since JDK 1.8
 */
@RunWith(VertxUnitRunner.class)
public class MyVerticleApplicationTest {


    private Vertx vertx;

    @Before
    public void setUp(TestContext context) {
        vertx = Vertx.vertx();

        int port = 8081;
        DeploymentOptions options = new DeploymentOptions()
                .setConfig(new JsonObject().put("http.port", port));
        vertx.deployVerticle(MyVerticleApplication.class.getName(), options, context.asyncAssertSuccess());
    }

    @Test
    public void testMyVerticleApplication(TestContext context) {
        final Async async = context.async();

        vertx.createHttpClient()
                .getNow(8080, "localhost", "/", response -> {
                    response.handler(body -> {
                        context.assertTrue(body.toString().contains("hello"));
                        async.complete();
                    });
                });
    }


    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }
}