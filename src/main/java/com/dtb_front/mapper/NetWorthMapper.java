package com.dtb_front.mapper;

import com.dtb_front.domain.NetWorth;
import com.dtb_front.domain.NetWorthDto;
import com.dtb_front.domain.User;
import com.dtb_front.domain.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class NetWorthMapper {

    public NetWorth mapToNetWorth(final NetWorthDto netWorthDto) {
        return NetWorth.builder()
                .id(netWorthDto.getId())
                .realEstate(netWorthDto.getRealEstate())
                .cash(netWorthDto.getCash())
                .vehicles(netWorthDto.getVehicles())
                .savingsAndInvestments(netWorthDto.getSavingsAndInvestments())
                .foreignCurrencies(netWorthDto.getForeignCurrencies())
                .stocks(netWorthDto.getStocks())
                .collections(netWorthDto.getCollections())
                .homeContent(netWorthDto.getHomeContent())
                .otherAssets(netWorthDto.getOtherAssets())
                .mortgage(netWorthDto.getMortgage())
                .loans(netWorthDto.getLoans())
                .creditCards(netWorthDto.getCreditCards())
                .otherLiabilities(netWorthDto.getOtherLiabilities())
                .totalAssets(netWorthDto.getTotalAssets())
                .totalLiabilities(netWorthDto.getTotalLiabilities())
                .totalNetWorth(netWorthDto.getTotalNetWorth())
                .date(netWorthDto.getDate())
                .user(new User(1L, "John", "Shoggoth", LocalDateTime.now(), "PLN", false))
                //.user(userRepository.findById(netWorthDto.getUserId()).orElseThrow(UserNotFoundException::new))
                .build();
    }

    public NetWorthDto mapToNetWorthDto(final NetWorth netWorth) {
        return NetWorthDto.builder()
                .id(netWorth.getId())
                .realEstate(netWorth.getRealEstate())
                .cash(netWorth.getCash())
                .vehicles(netWorth.getVehicles())
                .savingsAndInvestments(netWorth.getSavingsAndInvestments())
                .foreignCurrencies(netWorth.getForeignCurrencies())
                .stocks(netWorth.getStocks())
                .collections(netWorth.getCollections())
                .homeContent(netWorth.getHomeContent())
                .otherAssets(netWorth.getOtherAssets())
                .mortgage(netWorth.getMortgage())
                .loans(netWorth.getLoans())
                .creditCards(netWorth.getCreditCards())
                .otherLiabilities(netWorth.getOtherLiabilities())
                .totalAssets(netWorth.getTotalAssets())
                .totalLiabilities(netWorth.getTotalLiabilities())
                .totalNetWorth(netWorth.getTotalNetWorth())
                .date(netWorth.getDate())
                //.userId(netWorth.getUser().getId())
                .userId(1L)
                .build();
    }

    public List<NetWorth> mapToNetWorthList(final List<NetWorthDto> netWorthDtoList) {
        return netWorthDtoList.stream()
                .map(this::mapToNetWorth)
                .collect(Collectors.toList());
    }

    public List<NetWorthDto> mapToNetWorthDtoList(final List<NetWorth> netWorthList) {
        return netWorthList.stream()
                .map(this::mapToNetWorthDto)
                .collect(Collectors.toList());
    }

    public NetWorth mapToNetWorthWithoutTotals(final NetWorthDto netWorthDto) {
        return NetWorth.builder()
                .id(netWorthDto.getId())
                .realEstate(netWorthDto.getRealEstate())
                .cash(netWorthDto.getCash())
                .vehicles(netWorthDto.getVehicles())
                .savingsAndInvestments(netWorthDto.getSavingsAndInvestments())
                .foreignCurrencies(netWorthDto.getForeignCurrencies())
                .stocks(netWorthDto.getStocks())
                .collections(netWorthDto.getCollections())
                .homeContent(netWorthDto.getHomeContent())
                .otherAssets(netWorthDto.getOtherAssets())
                .mortgage(netWorthDto.getMortgage())
                .loans(netWorthDto.getLoans())
                .creditCards(netWorthDto.getCreditCards())
                .otherLiabilities(netWorthDto.getOtherLiabilities())
                .date(netWorthDto.getDate())
                .user(new User(1L, "John", "Shoggoth", LocalDateTime.now(), "PLN", true))
                .build();
    }

    public NetWorthDto mapToNetWorthDtoWithoutTotals(final NetWorth netWorth) {
        return NetWorthDto.builder()
                .id(netWorth.getId())
                .realEstate(netWorth.getRealEstate())
                .cash(netWorth.getCash())
                .vehicles(netWorth.getVehicles())
                .savingsAndInvestments(netWorth.getSavingsAndInvestments())
                .foreignCurrencies(netWorth.getForeignCurrencies())
                .stocks(netWorth.getStocks())
                .collections(netWorth.getCollections())
                .homeContent(netWorth.getHomeContent())
                .otherAssets(netWorth.getOtherAssets())
                .mortgage(netWorth.getMortgage())
                .loans(netWorth.getLoans())
                .creditCards(netWorth.getCreditCards())
                .otherLiabilities(netWorth.getOtherLiabilities())
//                .totalAssets(netWorth.getTotalAssets())
//                .totalLiabilities(netWorth.getTotalLiabilities())
//                .totalNetWorth(netWorth.getTotalNetWorth())
                .date(netWorth.getDate())
                .userId(1L)
                .build();
    }

    public List<NetWorth> mapToNetWorthListWithoutTotals(final List<NetWorthDto> netWorthDtoList) {
        return netWorthDtoList.stream()
                .map(this::mapToNetWorthWithoutTotals)
                .collect(Collectors.toList());
    }

    public List<NetWorthDto> mapToNetWorthDtoListWithoutTotals(final List<NetWorth> netWorthList) {
        return netWorthList.stream()
                .map(this::mapToNetWorthDtoWithoutTotals)
                .collect(Collectors.toList());
    }
}
