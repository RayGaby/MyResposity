package cn.gov.hrss.ln.stuenroll.message;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.spring.Spring;

@Spring("messageController")
public class MessageController extends Controller implements I_MessageController {
	private I_MessageService i_MessageService;

	@Override
	public void getNotice() {
		Page page = i_MessageService.getNotice();
		renderJson(page);
	}
	@Override
	public void getImageFile() {
		String imageID = getPara("imageID");
		File file = i_MessageService.getImageFile(imageID);		
		if (file != null) {
			HttpServletResponse response = this.getResponse();
			ServletOutputStream outputStream;
			try {
				outputStream = response.getOutputStream();
				FileInputStream inputStream = new FileInputStream(file);
				int b = 0;
				byte[] buffer = new byte[512];
				b = inputStream.read(buffer);
				while (b != -1) {
					outputStream.write(buffer, 0, b);
					b = inputStream.read(buffer);
				}
				inputStream.close();
				outputStream.close();
				outputStream.flush();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		renderNull();
	}

	@Override
	public void getMessage() {
		String messageId = getPara("messageID");
		
		Page messagePage = i_MessageService.getMessage(messageId);
		renderJson(messagePage);
		
	}
	public I_MessageService getI_MessageService() {
		return i_MessageService;
	}

	public void setI_MessageService(I_MessageService i_MessageService) {
		this.i_MessageService = i_MessageService;
	}


}
