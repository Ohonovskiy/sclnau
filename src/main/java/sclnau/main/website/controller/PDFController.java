package sclnau.main.website.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/media")
public class PDFController {

    @GetMapping("/preview")
    public void preview(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
//    File file = new File("/home/<username>/e95433e8-fb08-4c63-ba57-70331903562f.pdf");
        File file = ResourceUtils.getFile("classpath:static/documents/bud/PRV.pdf");
        if (file.exists()) {
            byte[] data = null;
            FileInputStream input = null;

            try {
                input = new FileInputStream(file);
                data = new byte[input.available()];
                input.read(data);

                response.setContentType("application/pdf");
                response.getOutputStream().write(data);
                response.flushBuffer();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    if (input != null) {
                        input.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
}
