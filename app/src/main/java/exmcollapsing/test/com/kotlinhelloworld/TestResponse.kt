package exmcollapsing.test.com.kotlinhelloworld

/**
 * Created by Boosal on 2017/7/13.
 */
class TestResponse(var user_guides:MutableList<TypePhone>,var error_code:Int) {
    /**
     * user_guides : [{"id":1,"title":"支持手机型号","detail":"充电宝自带4根数据线，支持安卓，苹果，Type-C接口的手机及电子产品，如果您自带了充电线，充电宝上面的USB接口可以直接使用。","url":
     * "","created_at":"2017-04-19 00:00:00","updated_at":"2017-04-19 00:00:00"},{"id":2,"title":"押金规则","detail":"押金充值：第一次使用需要充值99元，若后续余额低于99元，则需要将账户余额充至不低于99元。\r押金退款：如果有未归还的充电宝时，不能进行提现操作。归还充电宝后，可以随时押金提现，约0-7个工作日退还到充值账户。","url":"","created_at":"2017-04-19 00:00:00","updated_at":"2017-04-19 00:00:00"},{"id":3,"title":"借出的充电宝是坏的怎么办","detail":"如果您借出的充电宝出现充电线损坏或者无法充电灯情况，可以报错充电宝，归还到机柜。","url":"","created_at":"2017-04-19 00:00:00","updated_at":"2017-04-19 00:00:00"},{"id":4,"title":"能否借多个充电宝","detail":"暂时不支持1人同时借多个充电宝。","url":"","created_at":"2017-04-19 00:00:00","updated_at":"2017-04-19 00:00:00"},{"id":5,"title":"充电宝插不进机柜卡槽内","detail":"可能设备场地网络稳定好，可以扫描机柜二维码报错，客服介入解决。","url":"","created_at":"2017-04-19 00:00:00","updated_at":"2017-04-19 00:00:00"}]
     * error_code : 0
     */
    inner class TypePhone(var id:Int,var title:String,var detail:String,var url:String,var created_at:String,var updated_at:String)
}
