export default class Test {
    public orders: Orders;
}

export class Orders {
    public pageListDTO: PageListDTO;
    public retCode: number;
    public appPlanExtraDTOs: AppPlanExtraDTOs[];
}

export class PageListDTO {
    public totalPage: number;
    public dataList: DataList[];
    public currentPage: number;
    public totalCount: number;
    public empty: boolean;
}

export class DataList {
    public orderType: number;
    public orderSource: number;
    public orderUnderwriteInfo: OrderUnderwriteInfo;
    public quantity: number;
    public addTime: string;
    public orderId: number;
    public refundStatus: number;
    public insuredsInfo: InsuredsInfo;
    public prodId: number;
    public payAmt: number;
    public applicantsInfo: ApplicantsInfo;
    public refundDesc: string;
    public goodsPrice: number;
    public prodName: string;
    public orderSourceFlag: number;
    public startTime: string;
    public endTime: string;
    public comId: number;
    public comName: string;
    public status: number;
    public repayStatus: number;
}

export class OrderUnderwriteInfo {
    public underwriting: string[];
    public rejected: string[];
    public underwrited: string[];
    public confirmed: string[];
}

export class InsuredsInfo {
    public insuredDTOs: InsuredDTOs[];
}

export class InsuredDTOs {
    public birthday: string;
    public insuPrice: number;
    public zipCode: string;
    public maritalStaus: number;
    public gender: number;
    public renewalIgnore: number;
    public insurantId: number;
    public cardNo: string;
    public jobxStr: string;
    public homeCommonProvince: string;
    public homeCommonProvinceStr: string;
    public appIndex: number;
    public engName: string;
    public homeCommonCountyStr: string;
    public commonCityStr: string;
    public appId: number;
    public homeCommonCounty: string;
    public fixedIncome: string;
    public insuSelectedPropDTOs: InsuSelectedPropDTOs[];
    public homeCommonCity: string;
    public homeAddress: string;
    public height: number;
    public homeTypeStr: string;
    public commonCountyStr: string;
    public quantity: number;
    public commonCity: string;
    public homeCommonCityStr: string;
    public cardType: string;
    public mobile: string;
    public insurantTypeStr: string;
    public commonCounty: string;
    public weight: number;
    public jobx: number;
    public commonProvince: string;
    public commonProvinceStr: string;
    public postalAddress: string;
    public insurantIndex: number;
    public name: string;
    public incomeSource: string;
    public homeType: string;
    public insurantType: string;
}

export class InsuSelectedPropDTOs {
    public optionVal: string;
    public propId: number;
    public optionId: number;
    public type: number;
    public optionName: string;
    public propName: string;
}

export class ApplicantsInfo {
    public applicantDTOs: ApplicantDTOs[];
}

export class ApplicantDTOs {
    public appSelectedPropDTOs: AppSelectedPropDTOs[];
    public appPlanIndex: number;
    public loginId: number;
    public addTime: string;
    public orderId: number;
    public insuFillType: number;
    public couponId: number;
    public points: number;
    public appRepayStatus: number;
    public bonusPoints: number;
    public prodKind: string;
    public commonCityStr: string;
    public appId: number;
    public startingTime: string;
    public appCounty: string;
    public prodName: string;
    public insComName: string;
    public bankAccount: string;
    public appMarketPrice: number;
    public chargeFee: number;
    public residueFlag: number;
    public bankType: string;
    public prodType: number;
    public appStatus: number;
    public commonProvinceStr: string;
    public appQuantity: number;
    public appPayType: number;
    public postalAddress: string;
    public beneficiary: string;
    public updaterNo: number;
    public appCardType: string;
    public fullSubAmt: number;
    public discountAmt: number;
    public maritalStaus: number;
    public insComId: number;
    public accountName: string;
    public prodId: number;
    public appPostFee: number;
    public appEmail: string;
    public appIndex: number;
    public appGender: number;
    public appPrepayPrice: number;
    public appType: string;
    public endingTime: string;
    public usedDiscount: number;
    public pointsDeducAmt: number;
    public serviceAmt: number;
    public endingHour: string;
    public commonCity: string;
    public couponDeductAmt: number;
    public appName: string;
    public appRepayAmt: number;
    public updateTime: string;
    public appPlanId: number;
    public commonProvince: string;
    public appBirthday: string;
    public appPrice: number;
    public startingHour: string;
    public appCountyStr: string;
    public appMobile: string;
    public buyType: number;
    public endingMinute: string;
    public invoiceStatus: number;
    public appZipCode: string;
    public appCardNo: string;
    public enteringStatus: number;
    public adderNo: number;
}

export class AppSelectedPropDTOs {
    public optionVal: string;
    public propId: number;
    public optionId: number;
    public type: number;
    public optionName: string;
    public propName: string;
}

export class AppPlanExtraDTOs {
    public appPlanIndex: number;
    public benefitesCategoryDtos: string[];
    public orderId: number;
    public appId: number;
    public advanceClaimsProdFlag: boolean;
    public paySide: number;
}