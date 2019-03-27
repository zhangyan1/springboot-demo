#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.web.filter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger("access");

    private static final String[] HEADERS_ABOUT_CLIENT_IP = { "X-Forwarded-For", "Proxy-Client-IP",
            "WL-Proxy-Client-IP", "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) srequest;
        HttpServletResponse response = (HttpServletResponse) sresponse;

        long start = System.nanoTime();
        chain.doFilter(request, response);
        long during = System.nanoTime() - start;
        int status = response.getStatus();
        logger.info(parseHttpRequest(request, during) + "; status:" + status);

    }

    public static Cookie getCookie(String name, Cookie[] cookies) {
        if (StringUtils.isBlank(name))
            return null;
        if (null == cookies || cookies.length == 0)
            return null;
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName()))
                return cookie;
        }
        return null;
    }

    private String parseHttpRequest(HttpServletRequest request, long during) {
        StringBuilder sb = new StringBuilder();
        sb.append("IP:").append(getClientIpAddr(request));
        sb.append("; TIME:").append((during / (1000 * 1000))).append("ms");
        sb.append("; URI:").append(request.getRequestURI());
        sb.append("; METHOD:").append(request.getMethod());
        sb.append("; UA:").append(request.getHeader("User-Agent"));
        sb.append("; COOKIE:").append(toCookiesString(request.getCookies()));
        sb.append("; HEADER:");
        Enumeration<String> header = request.getHeaderNames();
        while (header.hasMoreElements()) {
            String key = header.nextElement();
            sb.append(key).append("=[").append(request.getHeader(key)).append("]");
        }
        sb.append("; PARAMS:");
        Map<String, String[]> map = request.getParameterMap();
        for (String name : map.keySet()) {
            String[] values = map.get(name);
            sb.append("[").append(name).append("=").append(Arrays.toString(values));
        }
        return sb.toString();
    }

    public String getClientIpAddr(HttpServletRequest request) {
        for (String header : HEADERS_ABOUT_CLIENT_IP) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    private String toCookiesString(Cookie[] cookies) {
        if (cookies == null) {
            return null;
        }
        if (cookies.length == 0) {
            return "[]";
        }
        StringBuilder result = new StringBuilder("[");
        for (Cookie cookie : cookies) {
            result.append(toCookieString(cookie)).append(",");
        }
        result.deleteCharAt(result.length() - 1);
        return result.append("]").toString();
    }

    private String toCookieString(Cookie cookie) {
        return cookie == null ? null : "{\"" + cookie.getName() + "\":\"" + cookie.getValue() + "\"}";
    }

    private String toCookiesString(Collection<Cookie> collections) {
        return collections == null ? null : toCookiesString(collections.toArray(new Cookie[collections.size()]));
    }

    @Override
    public void destroy() {

    }

}
