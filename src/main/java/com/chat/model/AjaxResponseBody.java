package com.chat.model;

import java.util.List;

public class AjaxResponseBody {
	 String msg;
	    List<Mensage> result;
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public List<Mensage> getResult() {
			return result;
		}
		public void setResult(List<Mensage> result) {
			this.result = result;
		}

}
