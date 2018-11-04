package business.admin.book.web;
import business.admin.book.service.BookService;
import business.admin.book.service.impl.BookServiceImpl;
import entity.BookTypes;
import entity.Books;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import util.BaseServlet;
import util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * @author chenlin
 */
@WebServlet(name = "BookServlet",urlPatterns = "/admin/book")
@SuppressWarnings("unchecked")
public class BookServlet extends BaseServlet {
        private BookService bookService = new BookServiceImpl();
    private void queryAllTypes(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<BookTypes> bookTypes = bookService.queryAllType();
        request.setAttribute("bookTypes",bookTypes);
        request.getRequestDispatcher("/pages/admin/book/add.jsp").forward(request,response);
    }
    private void addBook(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DiskFileItemFactory factory = new DiskFileItemFactory(1024,new File("D:/temp/"));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(1024*1024*5);
        upload.setHeaderEncoding("UTF-8");
        try {
            List<FileItem> fileItemList = upload.parseRequest(request);
            Books books=new Books();
            List<Integer> typeId= new ArrayList<>();
            for (FileItem item:fileItemList
                    ) {
                if (item.isFormField()) {
                    if (item.getFieldName().equals("name")){
                        books.setName(item.getString("utf-8"));
                        continue;
                    }if (item.getFieldName().equals("price")){
                        books.setPrice(Double.parseDouble(item.getString("utf-8")));
                        continue;
                    }if (item.getFieldName().equals("auth")){
                        books.setAuthor(item.getString("utf-8"));
                        continue;
                    }if(item.getFieldName().equals("rebate")){
                        books.setRebate(Double.parseDouble(item.getString("utf-8")));
                        continue;
                    }if (item.getFieldName().equals("stock")){
                        books.setStock(Integer.parseInt(item.getString("utf-8")));
                        continue;
                    }if (item.getFieldName().equals("publisher")){
                        books.setPublisher(item.getString("utf-8"));
                        continue;
                    }if (item.getFieldName().equals("types")){
                         typeId.add(Integer.parseInt(item.getString("utf-8")));
                    }
                }else{
                    String fileName = item.getName();
                    fileName = WebUtil.uuid()+fileName.substring(fileName.lastIndexOf("."));
                    InputStream is = item.getInputStream();
                    FileUtils.copyInputStreamToFile(is,new File(this.getServletContext().getRealPath("/imgs")+"/"+fileName));
                    books.setImg(fileName);
                    item.delete();
                }
            }
            books.setTypeId(typeId);
            bookService.insertBook(books);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
