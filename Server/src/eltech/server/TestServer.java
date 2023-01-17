package eltech.server;

import com.caucho.hessian.client.HessianProxyFactory;
import com.caucho.hessian.server.HessianServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;

public class TestServer extends HttpServlet {
    public interface BasicAPI {
        public String hello();
    }
    public static void main(String[] args) {
        String url = "http://hessian.caucho.com/test/test";

        HessianProxyFactory factory = new HessianProxyFactory();
        BasicAPI basic = null;
        try {
            basic = (BasicAPI) factory.create(BasicAPI.class, url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("hello(): " + basic.hello());
    }
    public class BasicService extends HessianServlet implements BasicAPI {
        private String _greeting = "Hello, world";

        public void setGreeting(String greeting)
        {
            _greeting = greeting;
        }

        public String hello()
        {
            return _greeting;
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String a = req.getParameter("a");
        String b = req.getParameter("b");

        PrintWriter writer = resp.getWriter();
        if (a != null & b != null) {
         //   int i = Integer.parseInt(a) + Integer.parseInt(b);
            String i = a + b;
            writer.println(a + "+" + b + "=" + i);
        } else {
            writer.println("parameter   = null");
        }
        writer.close();
    }
}

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String a = req.getParameter("a");
//        String b = req.getParameter("b");
//
//        PrintWriter writer = resp.getWriter();
//        if (a != null && b != null) {
//            int i = Integer.parseInt(a) + Integer.parseInt(b);
//            writer.println(a + "+" + b + "=" + i);
//        }else{
//            writer.println("parameter = null");
//        }
//        writer.close();
//    }
//}
