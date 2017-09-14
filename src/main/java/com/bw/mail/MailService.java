package com.bw.mail;

/**
 * Created by lenovo on 2017/7/15.
 */
public interface MailService {

  public void sendSimpleMail(String to, String subject, String content);

}
