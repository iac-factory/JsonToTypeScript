export default class Test {
  public orders?: Orders;
}

export class Orders {
  public retCode?: number;
  public pageListDTO?: PageListDTO;
  public appPlanExtraDTOs?: AppPlanExtraDTOs[];
}

export class PageListDTO {
  public currentPage?: number;
  public totalPage?: number;
  public totalCount?: number;
  public dataList?: DataList[];
  public empty?: boolean;
}

export class DataList {
  public orderId?: number;
  public refundStatus?: number;
  public refundDesc?: string;
  public goodsPrice?: number;
  public payAmt?: number;
  public status?: number;
  public repayStatus?: number;
  public orderSourceFlag?: number;
  public orderSource?: number;
  public orderType?: number;
  public addTime?: string;
  public applicantsInfo?: ApplicantsInfo;
  public insuredsInfo?: InsuredsInfo;
  public orderUnderwriteInfo?: OrderUnderwriteInfo;
  public endTime?: string;
  public comId?: number;
  public startTime?: string;
  public prodId?: number;
  public comName?: string;
  public prodName?: string;
  public quantity?: number;
}

export class ApplicantsInfo {
  public applicantDTOs?: ApplicantDTOs[];
}

export class ApplicantDTOs {
  public appSelectedPropDTOs?: AppSelectedPropDTOs[];
  public prodId?: number;
  public appId?: number;
  public startingTime?: string;
  public startingHour?: string;
  public endingTime?: string;
  public endingHour?: string;
  public endingMinute?: string;
  public appName?: string;
  public appBirthday?: string;
  public appGender?: number;
  public commonProvince?: string;
  public commonCity?: string;
  public appCounty?: string;
  public addTime?: string;
  public appPlanIndex?: number;
  public appIndex?: number;
  public appPlanId?: number;
  public orderId?: number;
  public loginId?: number;
  public insComId?: number;
  public insComName?: string;
  public prodType?: number;
  public prodKind?: string;
  public prodName?: string;
  public maritalStaus?: number;
  public appCardType?: string;
  public appCardNo?: string;
  public appMobile?: string;
  public appEmail?: string;
  public commonProvinceStr?: string;
  public commonCityStr?: string;
  public appCountyStr?: string;
  public beneficiary?: string;
  public appQuantity?: number;
  public residueFlag?: number;
  public serviceAmt?: number;
  public discountAmt?: number;
  public pointsDeducAmt?: number;
  public fullSubAmt?: number;
  public bonusPoints?: number;
  public appPrice?: number;
  public appPostFee?: number;
  public chargeFee?: number;
  public appMarketPrice?: number;
  public usedDiscount?: number;
  public couponId?: number;
  public couponDeductAmt?: number;
  public points?: number;
  public appRepayStatus?: number;
  public appRepayAmt?: number;
  public appPrepayPrice?: number;
  public appPayType?: number;
  public invoiceStatus?: number;
  public appType?: string;
  public insuFillType?: number;
  public buyType?: number;
  public enteringStatus?: number;
  public appStatus?: number;
  public adderNo?: number;
  public updaterNo?: number;
  public updateTime?: string;
  public bankType?: string;
  public bankAccount?: string;
  public accountName?: string;
  public appZipCode?: string;
  public postalAddress?: string;
}

export class AppSelectedPropDTOs {
  public propId?: number;
  public optionId?: number;
  public type?: number;
  public optionVal?: string;
  public propName?: string;
  public optionName?: string;
}

export class InsuredsInfo {
  public insuredDTOs?: InsuredDTOs[];
}

export class InsuredDTOs {
  public insuSelectedPropDTOs?: InsuSelectedPropDTOs[];
  public insurantType?: string;
  public name?: string;
  public gender?: number;
  public birthday?: string;
  public appIndex?: number;
  public insurantIndex?: number;
  public insurantId?: number;
  public appId?: number;
  public insurantTypeStr?: string;
  public quantity?: number;
  public cardType?: string;
  public cardNo?: string;
  public mobile?: string;
  public maritalStaus?: number;
  public insuPrice?: number;
  public renewalIgnore?: number;
  public engName?: string;
  public commonProvince?: string;
  public commonCity?: string;
  public commonCounty?: string;
  public homeCommonProvince?: string;
  public homeCommonCity?: string;
  public homeCommonCounty?: string;
  public homeType?: string;
  public commonProvinceStr?: string;
  public commonCityStr?: string;
  public commonCountyStr?: string;
  public homeTypeStr?: string;
  public homeAddress?: string;
  public homeCommonProvinceStr?: string;
  public homeCommonCityStr?: string;
  public homeCommonCountyStr?: string;
  public height?: number;
  public weight?: number;
  public jobx?: number;
  public postalAddress?: string;
  public zipCode?: string;
  public jobxStr?: string;
  public fixedIncome?: string;
  public incomeSource?: string;
}

export class InsuSelectedPropDTOs {
  public propId?: number;
  public optionId?: number;
  public type?: number;
  public optionVal?: string;
  public propName?: string;
  public optionName?: string;
}

export class OrderUnderwriteInfo {
  public confirmed?: string[];
  public underwriting?: string[];
  public underwrited?: string[];
  public rejected?: string[];
}

export class AppPlanExtraDTOs {
  public appPlanIndex?: number;
  public paySide?: number;
  public benefitesCategoryDtos?: string[];
  public advanceClaimsProdFlag?: boolean;
  public orderId?: number;
  public appId?: number;
}