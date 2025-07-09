package github.nbcamp.lectureflow.global.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String bearerJwt = getTokenFromHeader(request);

            if (StringUtils.hasText(bearerJwt) && jwtUtil.validateToken(bearerJwt)) {
                setSecurityContextHolder(bearerJwt);
            }
            filterChain.doFilter(request, response);

        } catch (Exception ex) {
            log.error("Could not set user authentication", ex);
            filterChain.doFilter(request, response);
        }

    }

    private String getTokenFromHeader(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(JwtUtil.BEARER_PREFIX.length());
        }
        return null;
    }

    private void setSecurityContextHolder(String jwt) {

        Long memberId = jwtUtil.extractMemberId(jwt);
        String role = jwtUtil.extractRoles(jwt);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                memberId, "", List.of(new SimpleGrantedAuthority("ROLE_" + role))
        ));
    }
}
