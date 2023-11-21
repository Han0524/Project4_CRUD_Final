package com.example.common;

import com.example.bean.BoardVO;
import com.example.dao.BoardDAO;
import com.example.util.JDBCUtil2;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class FileUpload {
    public static void deleteFile(HttpServletRequest request, String filename){
        String filePath = request.getServletContext().getRealPath("upload");

        File f = new File(filePath + "/" + filename);
        if(f.exists()) f.delete();
    }
    public BoardVO uploadPhoto(HttpServletRequest request) {
        String filename = "";
        int sizeLimit = 15 * 1024 * 1024;

        String realPath = request.getServletContext().getRealPath("upload");
        System.out.println(realPath);

        File dir = new File(realPath);
        if(!dir.exists()) dir.mkdirs();

        BoardVO one = null;
        MultipartRequest multipartRequest = null;
        try{
            multipartRequest = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

            filename = multipartRequest.getFilesystemName("photo");

            one = new BoardVO();
            String sid = multipartRequest.getParameter("id");
            if(sid != null && !sid.equals("")) one.setSid((Integer.parseInt(sid)));
            one.setUserid(multipartRequest.getParameter("userid"));
            one.setUname(multipartRequest.getParameter("uname"));
            one.setPassword(multipartRequest.getParameter("password"));
            one.setEmail(multipartRequest.getParameter("email"));
            one.setPhone_num(multipartRequest.getParameter("phone_num"));

            if(sid != null && !sid.equals("")){
                BoardDAO dao = new BoardDAO();
                String oldfilename = dao.getPhotoFilename(Integer.parseInt(sid));
                if(filename!=null && oldfilename!= null)
                    FileUpload.deleteFile(request, oldfilename);
                else if(filename==null && oldfilename!=null)
                    filename = oldfilename;
            }
            one.setPhoto(filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return one;
    }
}
