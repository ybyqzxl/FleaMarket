package panda.li.fleamarket.json;

import java.util.List;

public class ReplyJson {
	// List<RequirementReply> requirementReplies;
	List<Reply> reply;

	public void setReply(List<Reply> reply) {
		this.reply = reply;
	}

	public List<Reply> getReply() {
		return reply;
	}

	/*
	 * public void setRequirementReplies(List<RequirementReply>
	 * requirementReplies) { this.requirementReplies = requirementReplies; }
	 * 
	 * public List<RequirementReply> getRequirementReplies() { return
	 * requirementReplies; }
	 */
	public static class Reply {
		private String replyCode;
		private String replyContent;
		private long timeStamp;

		public void setReplyCode(String replyCode) {
			this.replyCode = replyCode;
		}

		public String getReplyCode() {
			return replyCode;
		}

		public void setReplyContent(String replyContent) {
			this.replyContent = replyContent;
		}

		public String getReplyContent() {
			return replyContent;
		}

		public void setTimeStamp(long timeStamp) {
			this.timeStamp = timeStamp;
		}

		public long getTimeStamp() {
			return timeStamp;
		}
	}

}
