package panda.li.fleamarket.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.iti.common.util.FileUtil;
import org.iti.http.interfaces.enums.HttpResponseState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import panda.li.fleamarket.abstracthttp.FleaMarketHttpInterfaceAction;

@Controller("StorageUpload")
@Scope("prototype")
public class StorageUpload extends FleaMarketHttpInterfaceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName;
	private File uploadFile;
	private String savePath;

	@Override
	public String execute() throws Exception {
		try {
			if (this.fileName == null || this.fileName.trim().equals("") || this.uploadFile == null) {
				this.responState = HttpResponseState.REQ_PARAMS_ERR.name();
				return ActionSupport.ERROR;
			}
			this.responResult = save();
		} catch (Exception e) {
			this.responState = HttpResponseState.REQ_UNKNOW_ERR.name();
			e.printStackTrace();
		}
		return ActionSupport.SUCCESS;
	}

	public String save() {
		String savePath = ServletActionContext.getServletContext().getRealPath(getSavePath());
		File imageDir = new File(savePath);
		if (!imageDir.exists()) {
			imageDir.mkdirs();
		}
		File to = new File(imageDir, new Date().getTime() + fileName);
		if (uploadFile != null && uploadFile.exists()) {
			try {
				FileUtil.write(uploadFile, to);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(getFileNameByUrl(to.getAbsolutePath()));
		String name = getFileNameByUrl(to.getAbsolutePath());
		return name;
	}

	public String getFileNameByUrl(String url) {
		if (url == null || url.length() == 0) {
			return null;
		}
		int index = url.lastIndexOf("/");
		String totalName;
		if (index < 0) {
			totalName = url;
		} else {
			totalName = url.substring(index + 1);
		}

		return totalName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	@Override
	public String getResponState() {
		return responState;
	}

	@Override
	public String getResponResult() {
		return responResult;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

}
