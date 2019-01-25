package com.admin.common.logback;

import ch.qos.logback.core.PropertyDefinerBase;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 通用ip获取方法
 * @author: wugang
 * @version: 1.0
 */
@Slf4j
public class IpProperty extends PropertyDefinerBase {

    /**默认值*/
    private static final String DEFAULT_VALUE = "ip";

    @Override
    public String getPropertyValue() {
        Enumeration<NetworkInterface> netInterfaces;
        List<String> ipList = new ArrayList<>();
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    InetAddress inet = ips.nextElement();
                    if (inet instanceof Inet4Address && (!inet.isLoopbackAddress())) {
                        ipList.add(inet.getHostAddress());
                    }
                }
            }
            if (CollectionUtils.isEmpty(ipList)) {
                InetAddress addr = InetAddress.getLocalHost();
                ipList.add(addr.getHostAddress());
                log.debug(String.format("读取到本机IP地址为【%s】", addr.getHostAddress()));
            }
            return StringUtils.join(ipList.toArray(), "_");
        } catch (Exception e) {
            log.error("获取本级网络IP地址出现异常", e);
        }
        return DEFAULT_VALUE;

    }

}