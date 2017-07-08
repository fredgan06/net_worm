package cn.g_open.net_worm.domainModel;

public class loginUser
{
    // 客户登入
    private String clientIp;
    private boolean failure;

    public String getClientIp()
    {
        return clientIp;
    }

    public void setClientIp(String clientIp)
    {
        this.clientIp = clientIp;
    }

    public boolean isFailure()
    {
        return failure;
    }

    public void setFailure(boolean failure)
    {
        this.failure = failure;
    }

}
