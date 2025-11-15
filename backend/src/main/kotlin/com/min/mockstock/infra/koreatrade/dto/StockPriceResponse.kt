package com.min.mockstock.infra.koreatrade.dto

import com.fasterxml.jackson.annotation.JsonAlias

data class StockPriceResponse(

    /** 성공/실패 여부 (성공: "success", 실패: "fail") */
    @JsonAlias("rt_cd")
    val resultCode: String,

    /** 응답 코드 */
    @JsonAlias("msg_cd")
    val messageCode: String,

    /** 응답 메시지 */
    @JsonAlias("msg1")
    val message: String,

    /** 응답 상세 데이터 (실제 주식 정보) */
    @JsonAlias("output")
    val output: StockOutputDetail
)

// 주식 상세 정보를 담는 output 객체
data class StockOutputDetail(

    @JsonAlias("rprs_mrkt_kor_name")
    val marketKoreanName: String,

    @JsonAlias("insn_pbnt_yn")
    val isPbnt: String,

    @JsonAlias("stck_prpr")
    val stockPresentPrice: String,

    @JsonAlias("prdy_vrss")
    val previousDayVs: String,

    @JsonAlias("prdy_vrss_sign")
    val previousDayVsSign: String,

    @JsonAlias("prdy_ctrt")
    val previousDayRate: String,

    @JsonAlias("acml_tr_pbmn")
    val accumulatedTradeMoney: String,

    @JsonAlias("acml_vol")
    val accumulatedVolume: String,

    @JsonAlias("prdy_vol")
    val previousDayVolume: String,

    @JsonAlias("prdy_vrss_vol_rate")
    val previousDayVolumeRate: String,

    @JsonAlias("bstp_kor_isnm")
    val industryKoreanName: String,

    @JsonAlias("sltr_yn")
    val isSettlementTrading: String,

    @JsonAlias("mang_issu_yn")
    val isManagementIssue: String,

    @JsonAlias("trht_yn")
    val isTradeHalt: String,

    @JsonAlias("oprc_rang_cont_yn")
    val isOpeningPriceRangeExtended: String,

    @JsonAlias("vlnt_fin_cls_code")
    val volatileFinanceClassCode: String,

    @JsonAlias("stck_prdy_clpr")
    val previousClosingPrice: String,

    @JsonAlias("stck_oprc")
    val openingPrice: String,

    @JsonAlias("prdy_clpr_vrss_oprc_rate")
    val previousCloseVsOpeningRate: String,

    @JsonAlias("oprc_vrss_prpr_sign")
    val openingVsPresentSign: String,

    @JsonAlias("oprc_vrss_prpr")
    val openingVsPresent: String,

    @JsonAlias("stck_hgpr")
    val highestPrice: String,

    @JsonAlias("prdy_clpr_vrss_hgpr_rate")
    val previousCloseVsHighRate: String,

    @JsonAlias("hgpr_vrss_prpr_sign")
    val highVsPresentSign: String,

    @JsonAlias("hgpr_vrss_prpr")
    val highVsPresent: String,

    @JsonAlias("stck_lwpr")
    val lowestPrice: String,

    @JsonAlias("prdy_clpr_vrss_lwpr_rate")
    val previousCloseVsLowRate: String,

    @JsonAlias("lwpr_vrss_prpr_sign")
    val lowVsPresentSign: String,

    @JsonAlias("lwpr_vrss_prpr")
    val lowVsPresent: String,

    @JsonAlias("marg_rate")
    val marginRate: String,

    @JsonAlias("crdt_rate")
    val creditRate: String,

    @JsonAlias("crdt_able_yn")
    val isCreditAble: String,

    @JsonAlias("elw_pblc_yn")
    val isElwIssued: String,

    @JsonAlias("stck_mxpr")
    val stockUpperLimitPrice: String,

    @JsonAlias("stck_llam")
    val stockLowerLimitPrice: String,

    @JsonAlias("bstp_cls_code")
    val industryClassCode: String,

    @JsonAlias("stck_sdpr")
    val standardPrice: String,

    @JsonAlias("vlnt_deal_cls_name")
    val volatilityDealClassName: String,

    @JsonAlias("new_lstn_cls_name")
    val newListingClassName: String,

    @JsonAlias("divi_app_cls_code")
    val dividendApplyClassCode: String,

    @JsonAlias("short_over_cls_code")
    val shortOverClassCode: String,

    @JsonAlias("vi_cls_code")
    val viClassCode: String,

    @JsonAlias("low_current_yn")
    val isLowCurrent: String,

    @JsonAlias("ssts_hot_yn")
    val isHot: String,

    @JsonAlias("stange_runup_yn")
    val isStrangeRunup: String,

    @JsonAlias("invt_caful_yn")
    val isInvestmentCaution: String,

    @JsonAlias("mrkt_warn_cls_code")
    val marketWarningClassCode: String,

    @JsonAlias("short_over_yn")
    val isShortOver: String
)