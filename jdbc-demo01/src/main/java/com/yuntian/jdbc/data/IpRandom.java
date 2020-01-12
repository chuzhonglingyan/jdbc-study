package com.yuntian.jdbc.data;

import java.util.Random;

/**
 * 随机ip地址
 * 代码源于网络 由kingYiFan整理  create2019/05/24
 */
public class IpRandom {

    // ip范围
    // 36.56.0.0-36.63.255.255
    // 61.232.0.0-61.237.255.255
    // 106.80.0.0-106.95.255.255
    // 121.76.0.0-121.77.255.255
    // 123.232.0.0-123.235.255.255
    // 139.196.0.0-139.215.255.255
    // 171.8.0.0-171.15.255.255
    // 182.80.0.0-182.92.255.255
    // 210.25.0.0-210.47.255.255
    // 222.16.0.0-222.95.255.255
    public static int[][] range = {{607649792, 608174079},
            {1038614528, 1039007743},
            {1783627776, 1784676351},
            {2035023872, 2035154943},
            {2078801920, 2079064063},
            {-1950089216, -1948778497},
            {-1425539072, -1425014785},
            {-1236271104, -1235419137},
            {-770113536, -768606209},
            {-569376768, -564133889},
    };

    private static Random random = new Random();

    public static String getRandomIp() {
        Random rdint = new Random();
        int index = rdint.nextInt(10);
        return num2ip(range[index][0] + random.nextInt(range[index][1] - range[index][0]));
    }


    /**
     * 将十进制转换成IP地址
     *
     * @param ip
     * @return
     */
    public static String num2ip(int ip) {
        int[] b = new int[4];
        StringBuilder x = new StringBuilder();
        b[0] = ((ip >> 24) & 0xff);
        b[1] = ((ip >> 16) & 0xff);
        b[2] = ((ip >> 8) & 0xff);
        b[3] = (ip & 0xff);
        x.append(Integer.toString(b[0])).append(".")
                .append(Integer.toString(b[1])).append(".")
                .append(Integer.toString(b[2])).append(".")
                .append(Integer.toString(b[3]));
        return x.toString();
    }

    public static void main(String[] args) {
        int count = 100000;
        for (int i = 0; i < count; i++) {
            String randomIp = getRandomIp();
            System.err.println(randomIp);
        }
    }

}