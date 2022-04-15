package com.dtb_front.service;

import com.dtb_front.client.NetWorthClient;
import com.dtb_front.domain.NetWorth;
import com.dtb_front.mapper.NetWorthMapper;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NetWorthService {
    private List netWorthList;
    private static NetWorthService netWorthService;
    private NetWorthClient netWorthClient = new NetWorthClient(new RestTemplate());
    private NetWorthMapper netWorthMapper = new NetWorthMapper();

    private NetWorthService() {
        this.netWorthList = exampleNetWorth();
    }

    public static NetWorthService getInstance() {
        if (netWorthService == null) {
            netWorthService = new NetWorthService();
        }
        return netWorthService;
    }

    public List getNetWorthList() {
        return new ArrayList<>(netWorthList);
    }

    public void addNetWorth(NetWorth netWorth) {
        this.netWorthList.add(netWorth);
    }

    private List exampleNetWorth() {
        List<NetWorth> netWorthList = netWorthMapper.mapToNetWorthList(netWorthClient.getNetWorth());
        System.out.println(netWorthList);

        return netWorthList;
    }

    public List filterNetWorthByDate(LocalDate begin, LocalDate end) {
        List<NetWorth> netWorthListByDate = netWorthMapper.mapToNetWorthList(netWorthClient.getNetWorthByDate(begin, end));
        System.out.println(begin);
        System.out.println(end);
        return netWorthListByDate;
    }

}
