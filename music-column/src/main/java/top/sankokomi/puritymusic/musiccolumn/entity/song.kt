package top.sankokomi.puritymusic.musiccolumn.entity

data class PlayListSongs(
    val code: Int,
    val privileges: List<PrivilegePlayListSongs>,
    val songs: List<SongPlayListSongs>
)

data class PrivilegePlayListSongs(
    val chargeInfoList: List<ChargeInfoPlayListSongs>,
    val cp: Int,
    val cs: Boolean,
    val dl: Int,
    val dlLevel: String,
    val downloadMaxBrLevel: String,
    val downloadMaxbr: Int,
    val fee: Int,
    val fl: Int,
    val flLevel: String,
    val flag: Int,
    val freeTrialPrivilege: FreeTrialPrivilegePlayListSongs,
    val id: Int,
    val maxBrLevel: String,
    val maxbr: Int,
    val payed: Int,
    val pl: Int,
    val plLevel: String,
    val playMaxBrLevel: String,
    val playMaxbr: Int,
    val preSell: Boolean,
    val rscl: Any,
    val sp: Int,
    val st: Int,
    val subp: Int,
    val toast: Boolean
)

data class SongPlayListSongs(
    val a: Any,
    val al: AlPlayListSongs,
    val alia: List<String>,
    val ar: List<ArPlayListSongs>,
    val awardTags: Any,
    val cd: String,
    val cf: String,
    val copyright: Int,
    val cp: Int,
    val crbt: Any,
    val djId: Int,
    val dt: Int,
    val entertainmentTags: Any,
    val fee: Int,
    val ftype: Int,
    val h: HPlayListSongs,
    val hr: HrPlayListSongs,
    val id: Int,
    val l: LPlayListSongs,
    val m: MPlayListSongs,
    val mark: Long,
    val mst: Int,
    val mv: Int,
    val name: String,
    val no: Int,
    val noCopyrightRcmd: Any,
    val originCoverType: Int,
    val originSongSimpleData: Any,
    val pop: Int,
    val pst: Int,
    val publishTime: Long,
    val resourceState: Boolean,
    val rt: String,
    val rtUrl: Any,
    val rtUrls: List<Any>,
    val rtype: Int,
    val rurl: Any,
    val s_id: Int,
    val single: Int,
    val songJumpInfo: Any,
    val sq: SqPlayListSongs,
    val st: Int,
    val t: Int,
    val tagPicList: Any,
    val tns: List<String>,
    val v: Int,
    val version: Int
)

data class ChargeInfoPlayListSongs(
    val chargeMessage: Any,
    val chargeType: Int,
    val chargeUrl: Any,
    val rate: Int
)

data class FreeTrialPrivilegePlayListSongs(
    val listenType: Any,
    val resConsumable: Boolean,
    val userConsumable: Boolean
)

data class AlPlayListSongs(
    val id: Int,
    val name: String,
    val pic: Long,
    val picUrl: String,
    val pic_str: String,
    val tns: List<String>
)

data class ArPlayListSongs(
    val alias: List<Any>,
    val id: Int,
    val name: String,
    val tns: List<Any>
)

data class HPlayListSongs(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Int
)

data class HrPlayListSongs(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Int
)

data class LPlayListSongs(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Int
)

data class MPlayListSongs(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Int
)

data class SqPlayListSongs(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Int
)