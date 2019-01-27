package com.admin.user.kafka;

public abstract class AbstractMsgListen<E>
{

//  private int threadNum = 8; //CPU数*2
  public void MsgProcess(String value)
  {
    try
    {
      MsgHandler(value);
    } catch (Exception e) {
    }
  }
  public abstract void MsgHandler(String value);
}
