import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;

    public void init() {
        productDAO = new ProductDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        List<Product> products = productDAO.getAllProducts();
        
        request.setAttribute("products", products);
        
        request.getRequestDispatcher("/WEB-INF/products.jsp").forward(request, response);
    } catch (Exception e) {
        e.printStackTrace(); 
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error");
    }
}

}
