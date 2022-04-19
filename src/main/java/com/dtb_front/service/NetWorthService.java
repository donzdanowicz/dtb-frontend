package com.dtb_front.service;

import com.dtb_front.client.NetWorthClient;
import com.dtb_front.domain.Entry;
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
        this.netWorthList = getNetWorthList();
    }

    public static NetWorthService getInstance() {
        if (netWorthService == null) {
            netWorthService = new NetWorthService();
        }
        return netWorthService;
    }

    public List getNetWorthList() {
        List<NetWorth> netWorthList = netWorthMapper.mapToNetWorthList(netWorthClient.getNetWorth());
        return netWorthList;
    }

    public void addNetWorth(NetWorth netWorth) {
        netWorthClient.addNetWorth(netWorthMapper.mapToNetWorthDto(netWorth));
    }

    public void updateNetWorth(NetWorth netWorth) {
        netWorthClient.updateNetWorth(netWorthMapper.mapToNetWorthDto(netWorth));
    }

    public List getNetWorthByDate(LocalDate begin, LocalDate end) {
        List<NetWorth> netWorthListByDate = netWorthMapper.mapToNetWorthList(netWorthClient.getNetWorthByDate(begin, end));
        return netWorthListByDate;
    }

    public void save(NetWorth netWorth) {
        addNetWorth(netWorth);
    }

    public void update(NetWorth netWorth) {
        updateNetWorth(netWorth);
    }

    public void delete(NetWorth netWorth) {
        netWorthClient.deleteNetWorth(netWorthMapper.mapToNetWorthDto(netWorth));
    }

}
