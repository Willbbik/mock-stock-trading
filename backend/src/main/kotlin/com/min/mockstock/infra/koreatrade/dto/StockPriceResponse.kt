package com.min.mockstock.infra.koreatrade.dto

import com.fasterxml.jackson.annotation.JsonAlias

data class StockPriceResponse(

    /** 성공/실패 여부 (성공: "success", 실패: "fail") */
    @JsonAlias("rt_cd")
    val resultCode: String? = null,

    /** 응답 코드 */
    @JsonAlias("msg_cd")
    val messageCode: String? = null,

    /** 응답 메시지 */
    @JsonAlias("msg1")
    val message: String? = null,

    /** 응답 상세 데이터 (실제 주식 정보) */
    @JsonAlias("output")
    val output: StockOutputDetail? = null
)

// 주식 상세 정보를 담는 output 객체
data class StockOutputDetail(

    @JsonAlias("rprs_mrkt_kor_name")
    val marketKoreanName: String? = null,

    @JsonAlias("insn_pbnt_yn")
    val isPbnt: String? = null,

    @JsonAlias("stck_prpr")
    val stockPresentPrice: String,

    @JsonAlias("prdy_vrss")
    val previousDayVs: String? = null,

    @JsonAlias("prdy_vrss_sign")
    val previousDayVsSign: String? = null,

    @JsonAlias("prdy_ctrt")
    val previousDayRate: String? = null,

    @JsonAlias("acml_tr_pbmn")
    val accumulatedTradeMoney: String? = null,

    @JsonAlias("acml_vol")
    val accumulatedVolume: String? = null,

    @JsonAlias("prdy_vol")
    val previousDayVolume: String? = null,

    @JsonAlias("prdy_vrss_vol_rate")
    val previousDayVolumeRate: String? = null,

    @JsonAlias("bstp_kor_isnm")
    val industryKoreanName: String? = null,

    @JsonAlias("sltr_yn")
    val isSettlementTrading: String? = null,

    @JsonAlias("mang_issu_yn")
    val isManagementIssue: String? = null,

    @JsonAlias("trht_yn")
    val isTradeHalt: String? = null,

    @JsonAlias("oprc_rang_cont_yn")
    val isOpeningPriceRangeExtended: String? = null,

    @JsonAlias("vlnt_fin_cls_code")
    val volatileFinanceClassCode: String? = null,

    @JsonAlias("stck_prdy_clpr")
    val previousClosingPrice: String? = null,

    @JsonAlias("stck_oprc")
    val openingPrice: String? = null,

    @JsonAlias("prdy_clpr_vrss_oprc_rate")
    val previousCloseVsOpeningRate: String? = null,

    @JsonAlias("oprc_vrss_prpr_sign")
    val openingVsPresentSign: String? = null,

    @JsonAlias("oprc_vrss_prpr")
    val openingVsPresent: String? = null,

    @JsonAlias("stck_hgpr")
    val highestPrice: String? = null,

    @JsonAlias("prdy_clpr_vrss_hgpr_rate")
    val previousCloseVsHighRate: String? = null,

    @JsonAlias("hgpr_vrss_prpr_sign")
    val highVsPresentSign: String? = null,

    @JsonAlias("hgpr_vrss_prpr")
    val highVsPresent: String? = null,

    @JsonAlias("stck_lwpr")
    val lowestPrice: String? = null,

    @JsonAlias("prdy_clpr_vrss_lwpr_rate")
    val previousCloseVsLowRate: String? = null,

    @JsonAlias("lwpr_vrss_prpr_sign")
    val lowVsPresentSign: String? = null,

    @JsonAlias("lwpr_vrss_prpr")
    val lowVsPresent: String? = null,

    @JsonAlias("marg_rate")
    val marginRate: String? = null,

    @JsonAlias("crdt_rate")
    val creditRate: String? = null,

    @JsonAlias("crdt_able_yn")
    val isCreditAble: String? = null,

    @JsonAlias("elw_pblc_yn")
    val isElwIssued: String? = null,

    @JsonAlias("stck_mxpr")
    val stockUpperLimitPrice: String? = null,

    @JsonAlias("stck_llam")
    val stockLowerLimitPrice: String? = null,

    @JsonAlias("bstp_cls_code")
    val industryClassCode: String? = null,

    @JsonAlias("stck_sdpr")
    val standardPrice: String? = null,

    @JsonAlias("vlnt_deal_cls_name")
    val volatilityDealClassName: String? = null,

    @JsonAlias("new_lstn_cls_name")
    val newListingClassName: String? = null,

    @JsonAlias("divi_app_cls_code")
    val dividendApplyClassCode: String? = null,

    @JsonAlias("short_over_cls_code")
    val shortOverClassCode: String? = null,

    @JsonAlias("vi_cls_code")
    val viClassCode: String? = null,

    @JsonAlias("low_current_yn")
    val isLowCurrent: String? = null,

    @JsonAlias("ssts_hot_yn")
    val isHot: String? = null,

    @JsonAlias("stange_runup_yn")
    val isStrangeRunup: String? = null,

    @JsonAlias("invt_caful_yn")
    val isInvestmentCaution: String? = null,

    @JsonAlias("mrkt_warn_cls_code")
    val marketWarningClassCode: String? = null,

    @JsonAlias("short_over_yn")
    val isShortOver: String? = null
)