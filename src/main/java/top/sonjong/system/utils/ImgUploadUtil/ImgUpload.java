package top.sonjong.system.utils.ImgUploadUtil;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;

public class ImgUpload {
    public final static String IMG_SAVE_LOCATION="D:/Program Files/JetBrains/apache-tomcat-8.5.54/webapps/uploadImgs/";
    public static String upload(MultipartFile multipartFile, HttpServletRequest request){
        try {
            // 获取项目路径
            String realPath = request.getSession().getServletContext()
                    .getRealPath("");
            InputStream inputStream = multipartFile.getInputStream();
            String contextPath = request.getContextPath();
            // 服务器根目录的路径
            String path = realPath.replace(contextPath.substring(1), "");
            // 根目录下新建文件夹uploadImgs，存放上传图片
            String uploadPath = path + "uploadImgs";
            // 获取文件名称
            String fileName = multipartFile.getOriginalFilename();
            String exts = fileName.substring(fileName.lastIndexOf(".") + 1);
            fileName = UUID.randomUUID().toString()+"."+exts;
            // 将文件上传的服务器根目录下的uploadImgs文件夹
            File file = new File(uploadPath, fileName);
            FileUtils.copyInputStreamToFile(inputStream, file);
            // 返回图片访问路径
            String url = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + request.getContextPath()+"/uploadImgs/" + fileName;
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
