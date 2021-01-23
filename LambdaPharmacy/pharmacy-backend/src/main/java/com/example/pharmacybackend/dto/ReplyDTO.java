package com.example.pharmacybackend.dto;

public class ReplyDTO {

    private Long comid;
    private Long userid;
    private String answer;

    public ReplyDTO() {
    }

    public ReplyDTO(Long comid, Long userid, String answer) {
        this.comid = comid;
        this.userid = userid;
        this.answer = answer;
    }

    /**
     * @return String return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * @return Long return the comid
     */
    public Long getComid() {
        return comid;
    }

    /**
     * @param comid the comid to set
     */
    public void setComid(Long comid) {
        this.comid = comid;
    }

    /**
     * @return Long return the userid
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

}
