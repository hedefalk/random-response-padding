package se.hedefalk;

import java.io.IOException;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Adds a random length random header to response
 */
public class RandomPaddingFilter implements Filter {

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd = new Random();
    private int maxLengthPlusOne; // Plus one

    private String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
    
    private String randomLengthString() {
        int length = rnd.nextInt(maxLengthPlusOne);
        return randomString(length);
    }

    public void init(FilterConfig config) throws ServletException {
        String maxLength = config.getInitParameter("max-length");
        if(maxLength != null) {
            this.maxLengthPlusOne = Integer.parseInt(maxLength) + 1;
        }
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) res;
        chain.doFilter(req, res);
        Cookie cookie = new Cookie("X-NO-BREACH", randomLengthString());
        response.addCookie(cookie);
    }

    public void destroy() {
    }

}
