package com.example.gateway.security;
import org.springframework.cloud.gateway.filter.GatewayFilterChain; import org.springframework.cloud.gateway.filter.GlobalFilter; import org.springframework.core.Ordered; import org.springframework.http.HttpStatus; import org.springframework.stereotype.Component; import org.springframework.web.server.ServerWebExchange; import reactor.core.publisher.Mono;
@Component public class AuthGlobalFilter implements GlobalFilter, Ordered {
    private final JwtUtil jwtUtil; public AuthGlobalFilter(JwtUtil jwtUtil){ this.jwtUtil = jwtUtil; }
    @Override public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (path.startsWith("/vehicles")) {
            String auth = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (auth == null || !auth.startsWith("Bearer ")) { exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED); return exchange.getResponse().setComplete(); }
            String token = auth.substring(7);
            if (!jwtUtil.validateToken(token)) { exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED); return exchange.getResponse().setComplete(); }
        }
        return chain.filter(exchange);
    }
    @Override public int getOrder(){ return -1; }
}
