package com.kuro.avalon.router;

import com.kuro.avalon.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * 路由
 *
 * @author kuro
 * @create 2020-03-10
 **/
@Configuration
public class AllRouter {

    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandler handler) {
        return nest(
                path("/user"),
                route(GET("/userDetail/{username}"), handler::getUserDetail)
                        .andRoute(POST("").and(accept(APPLICATION_JSON)), handler::addOne)
        );
    }
}
