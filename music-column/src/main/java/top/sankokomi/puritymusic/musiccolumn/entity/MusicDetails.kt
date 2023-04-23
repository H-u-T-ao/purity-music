package top.sankokomi.puritymusic.musiccolumn.entity

data class MusicDetails(
    val songs: List<Song>? = null,
    val privileges: List<Privileges>? = null,
    val code: Int = 0
)

data class Artist(
    val id: Int = 0,
    val name: String? = null,
    val tns: List<String>? = null,
    val alias: List<String>? = null
)

data class Album(
    val id: Int = 0,
    val name: String? = null,
    val picUrl: String? = null,
    val tns: List<String>? = null,
    val pic_str: String? = null,
    val pic: Long = 0
)

data class Quality(
    val br: Long = 0L,
    val fid: Int = 0,
    val size: Long = 0L,
    val vd: Int = 0,
    val sr: Int = 0
)

data class Song(
    val name: String? = null,
    val id: Long = 0L,
    val pst: Int = 0,
    val t: Int = 0,
    val ar: List<Artist>? = null,
    val alia: List<String>? = null,
    val pop: Int = 0,
    val st: Int = 0,
    val rt: String? = null,
    val fee: Int = 0,
    val v: Int = 0,
    val crbt: String? = null,
    val cf: String? = null,
    val al: Album? = null,
    val dt: Long = 0L,
    val h: Quality? = null,
    val m: Quality? = null,
    val l: Quality? = null,
    val sq: Quality? = null,
    val hr: String? = null,
    val a: String? = null,
    val cd: String? = null,
    val no: Int = 0,
    val rtUrl: String? = null,
    val ftype: Int = 0,
    val rtUrls: List<String>? = null,
    val djId: Int = 0,
    val copyright: Int = 0,
    val s_id: Int = 0,
    val mark: Int = 0,
    val originCoverType: Int = 0,
    val originSongSimpleData: String? = null,
    val tagPicList: String? = null,
    val resourceState: Boolean = false,
    val version: Int = 0,
    val songJumpInfo: String? = null,
    val entertainmentTags: String? = null,
    val awardTags: String? = null,
    val single: Int = 0,
    val noCopyrightRcmd: String? = null,
    val mv: Long = 0L,
    val mst: Int = 0,
    val cp: Int = 0,
    val rtype: Int = 0,
    val rurl: String? = null,
    val publishTime: Long = 0
)

data class FreeTrialPrivilege(
    val resConsumable: Boolean = false,
    val userConsumable: Boolean = false,
    val listenType: String? = null
)

data class ChargeInfoList(
    val rate: Long = 0L,
    val chargeUrl: String? = null,
    val chargeMessage: String? = null,
    val chargeType: Int = 0
)

data class Privileges(
    val id: Long = 0L,
    val fee: Int = 0,
    val payed: Int = 0,
    val st: Int = 0,
    val pl: Int = 0,
    val dl: Int = 0,
    val sp: Int = 0,
    val cp: Int = 0,
    val subp: Int = 0,
    val cs: Boolean = false,
    val maxbr: Long = 0L,
    val fl: Int = 0,
    val toast: Boolean = false,
    val flag: Int = 0,
    val preSell: Boolean = false,
    val playMaxbr: Long = 0L,
    val downloadMaxbr: Long = 0L,
    val maxBrLevel: String? = null,
    val playMaxBrLevel: String? = null,
    val downloadMaxBrLevel: String? = null,
    val plLevel: String? = null,
    val dlLevel: String? = null,
    val flLevel: String? = null,
    val rscl: String? = null,
    val freeTrialPrivilege: FreeTrialPrivilege? = null,
    val chargeInfoList: List<ChargeInfoList>? = null
)

