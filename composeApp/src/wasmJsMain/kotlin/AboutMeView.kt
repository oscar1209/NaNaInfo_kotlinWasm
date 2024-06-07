import androidx.compose.foundation.Image
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import androidx.compose.material3.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import kotlinproject.composeapp.generated.resources.*
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.infobutton
import kotlinproject.composeapp.generated.resources.infoframe
import kotlinproject.composeapp.generated.resources.oscar

@Immutable
data class memberData @OptIn(ExperimentalResourceApi::class) constructor(
    var name: String,
    var phone: String = "",
    var enName: String = "",
    var email: String = "",
    var titleForName: String = "",
    var peopleImage: DrawableResource,
    var info: String = "",
    var education: List<String> = listOf(),
    var experience: List<String> = listOf(),
    var major: List<String> = listOf(),
    var keyID: Int,
)

@Immutable
data class ContactUsData @OptIn(ExperimentalResourceApi::class) constructor(
    var title: String = "",
    var info: String = "",
    var email: String = "",
    var tel: String = "",
    var imageLoca: List<DrawableResource> = listOf(),
)


@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AboutMeView(
    scrollState: LazyListState,
    contextManager: ContextManager,
    handler: (contextManager: ContextManager) -> Unit
) {

    var peopleInfo: List<memberData> = listOf(
        memberData(
            name = "黃皓淵",
            phone = "0910817523",
            enName = "Huang Hao-Yuan",
            email = "oscar771209555@gamil.com",
            titleForName = "東華大學應用數學系博士, ＮaNa創辦人",
            peopleImage = Res.drawable.oscar,
            info = "明明臨床心理系畢業碩士卻唸了應用數學，中間曾經寫過小說，去過澳洲，在山上的小鎮一間中國人開的按摩店裡工作了一年。然後回來繼續寫，堅持什麼呢？其中有宛如神明般的小羊拐走了姊姊的親弟，也有殺死了自己的創造者最後被流放到地球的超先進古文明，大概就是像這樣的故事吧。\n" +
                    "\n" +
                    "什麼？你問這一切是怎麼跟數學扯上關係的？為了維持生計，我必須到一間補習班打工，因此就複習了國小、國中、高中的數學，接著便學了微積分，緊接著就在大學裡試圖搞懂高等微積分跟測度論了。\n" +
                    "\n" +
                    "或許與你又會想問，最後我又是怎麼學會右上角那些東東的？可是要詳述怎麼跟著教授一步一步學習類神經網路、從不懂程式到明白Ｍatlab、Swift還有等等的一切，就真的太漫長了。想說的只是，原本真心以為小說會這麼一直寫下去的。\n" +
                    "\n" +
                    "不過這樣倒也不賴。",

            education = listOf(
                "輔仁大學臨床心理系學士",
                "東華大學應用數學系碩士",
                "東華大學應用數學系博士（就讀中）"
            ),
            experience = listOf(
                "參與產學合作計畫『藍芽聽診器音訊分析的iOS APP實作與心音辨識應用",
                "參與產學合作計畫『iCloud雲端資料庫存取與即時拜訪路徑最佳化 』",
                "iOS app『暢遊花蓮』開發者（可於 Apple App Store下載）",
                "國衛院 app『行醫記錄器』更新至iOS 17（新版本僅供研究人員由特定網址下載）",
                "2020年於美國在台協會（American Institute in Taiwan）舉辦的「程式少女特訓營」當中當任活動講師"
            ),
            major = listOf(
                "iOS\\android App設計",
                "UI介面設計",
                "網頁前端與後端設計",
                "聲音\\視覺辨識"
            ),
            keyID = 1


        ))
        var contactUsDatas: List<ContactUsData> = listOf(
            ContactUsData(
                title = "關於NaNa",
                info = "很簡單，家裡有隻黑貓叫做NaNa，\n" +
                        "因為很喜歡，因此工作室便也就如此稱呼了。\n" +
                        "NaNa有著甜美的嗓音，很懂得利用自己的優勢換取被用罐罐餵食，\n" +
                        "希望我們也能如此這般，獲得各位持續的支持。",
                email = "eamil\n" +
                        "oscar771209555@gmail.com",
                tel = "電話\n" +
                        "0910-817-523",
                imageLoca = listOf(
                    Res.drawable.infoframe,
                    Res.drawable.infobutton,
                    Res.drawable.email,
                    Res.drawable.phone,
                    Res.drawable.my_mark,
                    Res.drawable.infoframe_small,
                )
            )
        )
    var textInput by remember { mutableStateOf("") }

    var maxHeight = contextManager.maxHeight
    var maxWidth = contextManager.maxWidth
    var decide = contextManager.decide
    var state = contextManager.stateControl
    var showMaxWidth: Dp = 2000.dp
    var nowWidth: Dp = if (maxWidth > showMaxWidth) showMaxWidth else maxWidth
    var backgroundColor = Color(red = 245, green = 245, blue = 245)
    var showMessageAlert by remember { mutableStateOf(false) }
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .height(contextManager.nowViewHeight*3)
            .width(maxWidth)
            .background(backgroundColor),
        state = scrollState
    ) {
        //about us
        if (contextManager.decide != "phone"){
            item(0) {
                var contextD = peopleInfo[0]
                Box(
                    modifier = Modifier
                        .height(contextManager.nowViewHeight*2)
                        .width(maxWidth),
                    contentAlignment = Alignment.Center
                ){
                    //layer1
                    Column(
                        modifier = Modifier
                            .height(contextManager.nowViewHeight*2)
                            .width(maxWidth)
                    ) {
                        Spacer(modifier = Modifier.height(120.dp))
                        Spacer(modifier = Modifier.height(300.dp).width(maxWidth).background(Color(0xffE2E2E2)))
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    //layer2
                    Column(
                        modifier = Modifier
                            .height(contextManager.nowViewHeight*2)
                            .width(nowWidth),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        //photo-----------------------------------------------------------
                        Spacer(modifier = Modifier.height(120.dp))
                        Row(
                            modifier = Modifier
                                .height(300.dp)
                                .width(nowWidth),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Spacer(modifier = Modifier.weight(0.3f))
                            Image(painter = painterResource(contextD.peopleImage) ,
                                "",
                                modifier = Modifier.fillMaxHeight(),
                                contentScale = ContentScale.FillHeight
                            )
                            Spacer(modifier = Modifier.width(100.dp))
                            Column(
                                modifier = Modifier.fillMaxHeight().width(nowWidth/2),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Spacer(modifier = Modifier.height(25.dp))
                                Row {
                                    Text(
                                        contextD.name+" ",
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 36.sp,
                                        lineHeight = 58.sp
                                    )
                                    Text(
                                        contextD.enName,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 24.sp,
                                        lineHeight = 58.sp
                                    )
                                }

                                Text(
                                    contextD.titleForName,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 24.sp,
                                    lineHeight = 58.sp
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    contextD.email,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp,
                                    lineHeight = 42.sp
                                )
                                Text(
                                    contextD.phone,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp,
                                    lineHeight = 42.sp
                                )
                                Spacer(modifier = Modifier.height(25.dp))
                            }
                            Spacer(modifier = Modifier.weight(0.7f))
                        }
                        Spacer(modifier = Modifier.height(80.dp))
                        //---------------------------------------------------------------
                        //text-----------------------------------------------------------
                        var rightLeftEdge: Dp = 200.dp
                        var infoWidth = 700.dp
                        var fristLong: Dp = if(nowWidth/2>infoWidth) nowWidth/2 else infoWidth
                        textTitle(
                            listOf(
                                Pair("個人簡介", fristLong),
                                Pair("專長",nowWidth-fristLong-rightLeftEdge)
                            )
                        )
                        Row(
                            modifier = Modifier.width(nowWidth),
                            verticalAlignment = Alignment.Top
                        ){
                            Spacer(modifier = Modifier.width(rightLeftEdge/2))
                            Column(
                                modifier = Modifier.width(fristLong),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    contextD.info,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 20.sp,
                                    lineHeight = 40.sp,
                                    modifier = Modifier.width(infoWidth-50.dp).align(alignment = Alignment.Start),
                                )
                            }
                            Column(
                                modifier = Modifier.width(nowWidth-fristLong-rightLeftEdge),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth().height(200.dp),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    for (j in contextD.major){
                                        Text(
                                            "．" + j,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 20.sp,
                                            lineHeight = 40.sp
                                        )
                                    }

                                }

                                textTitle(listOf(Pair("學歷",nowWidth-fristLong-rightLeftEdge)))
                                for (k in contextD.education){
                                    Text(
                                        "．" + k,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 20.sp,
                                        lineHeight = 40.sp,
                                        modifier = Modifier.align(alignment = Alignment.Start)
                                    )
                                }

                            }


                            Spacer(modifier = Modifier.width(rightLeftEdge/2))
                        }
                        var lastItemLong = 900.dp
                        Spacer(modifier = Modifier.height(100.dp))
                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier.width(nowWidth-rightLeftEdge)
                        ) {
                            Row(
                                modifier = Modifier.width(lastItemLong)
                            ){
                                textTitle(listOf(Pair("經歷",lastItemLong)))
                                //Spacer(modifier = Modifier.width(nowWidth-lastItemLong))
                            }
                            for (k in contextD.experience){
                                Text(
                                    "．" + k,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 20.sp,
                                    lineHeight = 40.sp,
                                    modifier = Modifier.align(alignment = Alignment.Start)
                                )
                            }
                        }
                        //---------------------------------------------------------------


                    }

                }


            }
            // contact us
            item(1) {

                Box(
                    modifier = Modifier
                        .height(contextManager.nowViewHeight)
                        .width(maxWidth),
                    contentAlignment = Alignment.Center
                ){
                    //layer1
                    Column(
                        modifier = Modifier
                            .height(contextManager.nowViewHeight)
                            .width(maxWidth),
                    ) {
                        Spacer(Modifier.height(40.dp))
                        Divider()

                    }
                    //layer2
                    //layer2&3 layout
                    var infoFrameRate: Double = 367.0/611.0
                    var infoFrameRateSmall: Double = 400.0/1000.0
                    var top = 150.dp
                    var lineWH = 50.dp
                    var contactHeight = contextManager.nowViewHeight-top-lineWH
                    var leftSecond = 120.dp
                    var leftThird = 120.dp
                    var leftFrist = if (contextManager.nowViewHeight-top-(4*lineWH.value).dp-leftSecond-leftThird<400.dp) contextManager.nowViewHeight-top-(4*lineWH.value).dp-leftSecond-leftThird else 400.dp
                        Column(
                        modifier = Modifier
                            .height(contextManager.nowViewHeight)
                            .width(nowWidth),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(Modifier.height(top+lineWH))
                        Row(
                            modifier = Modifier
                                .height(contactHeight)
                                .width(nowWidth),
                            verticalAlignment = Alignment.Top
                        ) {
                            Column (
                                Modifier.width(nowWidth/2).height(contactHeight)
                            ){
                                Spacer(modifier = Modifier.height(leftFrist+lineWH/2))
                                Divider()
                                Spacer(modifier = Modifier.height(leftSecond+lineWH))
                                Divider()
                                Spacer(modifier = Modifier.height(leftThird+lineWH))
                                Divider()
                            }
                            Spacer(modifier = Modifier.width(nowWidth/2))
                        }
                    }
                    //layer3
                    Column(
                        modifier = Modifier
                            .height(contextManager.nowViewHeight)
                            .width(nowWidth),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier = Modifier
                                .height(top)
                                .width(nowWidth),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                "聯絡我們",
                                fontSize = 36.sp,
                                fontWeight = FontWeight.ExtraBold

                            )
                        }
                        Spacer(Modifier.height(lineWH))
                        //var contactHeight = contextManager.nowViewHeight-200.dp
                        var contextD = contactUsDatas[0]
                        Row(
                            modifier = Modifier
                                .height(contactHeight)
                                .width(nowWidth),
                            verticalAlignment = Alignment.Top
                        ) {
                            var componentWidth = (nowWidth-50.dp)/2
                            var imageSwift: Boolean = componentWidth>600.dp && leftFrist.value < 600f*infoFrameRate
                            Column(
                                modifier = Modifier
                                    .height(contactHeight)
                                    .width(componentWidth),
                                horizontalAlignment = Alignment.End
                            ){
                                Row(
                                    modifier = Modifier
                                        .height(leftFrist)
                                        .width( if (imageSwift) (leftFrist.value/infoFrameRateSmall).dp else (leftFrist.value/infoFrameRate).dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                   Spacer(modifier = Modifier.width(20.dp))
                                    Box(
                                        modifier = Modifier
                                            .height(leftFrist)
                                            .width( if (imageSwift) (leftFrist.value/infoFrameRateSmall).dp else (leftFrist.value/infoFrameRate).dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource( if (imageSwift) contextD.imageLoca[5] else contextD.imageLoca[0]),
                                            "",
                                            contentScale = ContentScale.FillWidth
                                        )
                                        Column(
                                            modifier = Modifier
                                                .height(leftFrist)
                                                .width(if (imageSwift) (leftFrist.value/infoFrameRateSmall).dp else (leftFrist.value/infoFrameRate).dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Spacer(modifier = Modifier.weight(0.3f))
                                            Text(
                                                contextD.title,
                                                modifier = Modifier.width(((if (imageSwift) (leftFrist.value/infoFrameRateSmall) else (leftFrist.value/infoFrameRate)) *3/4).dp),
                                                fontSize = 24.sp,
                                                fontWeight = FontWeight.ExtraBold
                                            )
                                            Spacer(modifier = Modifier.weight(0.2f))
                                            Text(
                                                contextD.info,
                                                fontSize = 16.sp,
                                                lineHeight = 32.sp,
                                                fontWeight = FontWeight.Normal
                                            )
                                            Spacer(modifier = Modifier.weight(0.5f))
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(lineWH))
                                Row(
                                    modifier = Modifier
                                        .height(leftSecond)
                                        .width(componentWidth),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        contextD.email,
                                        fontSize = 24.sp,
                                        lineHeight = 36.sp,
                                        textAlign = TextAlign.End
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Image(
                                        painter = painterResource(contextD.imageLoca[2]),
                                        "",
                                        contentScale = ContentScale.FillHeight
                                    )
                                }

                                Spacer(modifier = Modifier.height(lineWH))
                                Row(
                                    modifier = Modifier
                                        .height(leftThird)
                                        .width(componentWidth),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        contextD.tel,
                                        fontSize = 24.sp,
                                        lineHeight = 36.sp,
                                        textAlign = TextAlign.End
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Image(
                                        painter = painterResource(contextD.imageLoca[3]),
                                        "",
                                        contentScale = ContentScale.FillHeight
                                    )
                                }

                                Spacer(modifier = Modifier.height(lineWH))
                            }
                            var gap = contextManager.nowViewHeight-top-(4*lineWH.value).dp-leftSecond-leftThird-leftFrist
                            var lineLengthLimit = if(gap>100.dp) 100.dp else if(gap>0.dp) gap else 0.dp
                            Column(
                                modifier = Modifier.width(lineWH).height(contactHeight-lineLengthLimit),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                VerticalDivider()
                            }

                            Column(
                                modifier = Modifier
                                    .height(contactHeight)
                                    .width(componentWidth),
                                horizontalAlignment = Alignment.Start
                            ){


                                 Spacer(modifier = Modifier.height(lineWH))
                                var style: TextStyle = TextStyle(
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 20.sp,
                                    lineHeight = 30.sp
                                )
                                OutlinedTextField(
                                    value = textInput,
                                    onValueChange = {
                                        val pattern = Regex("[a-zA-Z]")
                                        if (it.contains(pattern) || it == ""){
                                            textInput = it
                                        }

                                                    },
                                    textStyle = style,
                                    modifier = Modifier
                                        .height(contactHeight*2/3)
                                        .width(componentWidth*2/3),
                                    label = { Text("留下訊息") },
                                )
                                Spacer(modifier = Modifier.height(lineWH/2))
                                Button(
                                    onClick = {
                                        if (textInput != ""){
                                            showMessageAlert = true
                                        }

                                    },
                                    colors = ButtonColors(
                                        contentColor = Color(0xFFC3DBED),
                                        containerColor = Color(0xFFC3DBED),
                                        disabledContentColor = Color(0xffF5F5F5),
                                        disabledContainerColor = Color(0xffF5F5F5)

                                    )
                                ){
                                    Text(
                                        "確認",
                                        fontSize = 24.sp,
                                        modifier = Modifier.offset(y=-1.dp)
                                    )
                                }


                                /*
                                Spacer(modifier = Modifier.height(lineWH))
                                Image(
                                    painter = painterResource(contextD.imageLoca[4]),
                                    "",
                                    modifier = Modifier.height(contactHeight/2),
                                    contentScale = ContentScale.FillHeight
                                )
                                Spacer(Modifier.weight(1f))

                                 */
                                if (showMessageAlert){
                                    AlertDialog(onDismissRequest = { showMessageAlert = false },
                                        confirmButton = {
                                            Button(onClick = {
                                                textInput = ""
                                                showMessageAlert = false
                                            }){
                                                Text("確認", color = Color.White)
                                            }

                                        },
                                        modifier = Modifier,

                                        title = {
                                            Box(
                                                modifier = Modifier.fillMaxWidth(),
                                                contentAlignment = Alignment.CenterStart
                                            ){
                                                Image(
                                                    painter = painterResource(Res.drawable.my_mark),
                                                    "",
                                                    modifier = Modifier.height(40.dp),
                                                    contentScale = ContentScale.FillHeight
                                                )
                                                Row(
                                                    Modifier.fillMaxWidth(),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {

                                                    Spacer(Modifier.weight(0.5f))
                                                    Text("訊息", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
                                                    Spacer(Modifier.weight(0.5f))
                                                }
                                            }


                                        },
                                        text = {
                                            Box(
                                                modifier = Modifier.fillMaxWidth(),
                                                contentAlignment = Alignment.Center
                                            ){
                                                Text("留言已上傳")
                                            }

                                        }
                                    )

                                }

                            }
                        }

                    }

                }

            }
        }else{
            item(0){
                Column(
                    modifier = Modifier.fillMaxWidth().height(contextManager.nowViewHeight*2),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var contextD = peopleInfo[0]
                    Spacer(modifier = Modifier.height(40.dp))
                    var imageWidth: Dp = if(maxWidth/2<300.dp) maxWidth/2 else 300.dp
                    Row(
                        Modifier.fillMaxWidth().height(imageWidth).background(Color(0xffE2E2E2)),
                        verticalAlignment = Alignment.Top
                    ){
                        if (imageWidth==300.dp){
                            Spacer(Modifier.width(50.dp))
                        }
                        Image(painter = painterResource(contextD.peopleImage),"", contentScale = ContentScale.FillWidth, modifier = Modifier.width(imageWidth-50.dp))
                        Spacer(Modifier.weight(0.2f))
                        Column(
                            modifier = Modifier.width(maxWidth - imageWidth - if (imageWidth==300.dp) 50.dp else 0.dp)
                        ) {
                            Spacer(Modifier.weight(0.5f))
                            Text(contextD.name, fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(contextD.enName)
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(contextD.titleForName.split(" ")[0])
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(contextD.titleForName.split(" ")[1])
                            Spacer(Modifier.weight(0.5f))
                        }
                        Spacer(Modifier.weight(0.8f))
                    }
                    Column(
                        modifier = Modifier.width(maxWidth*3/4)
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        textTitle(
                            listOf(Pair("專長",maxWidth))
                        )
                        for (i in contextD.major){
                            Text("．" + i, lineHeight = 24.sp)
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                        textTitle(
                            listOf(Pair("學歷",maxWidth))
                        )
                        for (i in contextD.education){
                            Text("．" + i, lineHeight = 24.sp)
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                        textTitle(
                            listOf(Pair("經歷",maxWidth))
                        )
                        for (i in contextD.experience){
                            Row(modifier = Modifier.fillMaxWidth()){
                                Text("．")
                                Text(i, lineHeight = 24.sp)
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }


                }


            }

            item(1){

                var contextD = contactUsDatas[0]
                Column(
                    modifier = Modifier.fillMaxWidth().height(contextManager.nowViewHeight),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(40.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(40.dp))
                    var infoFrameRate: Double = 367.0/611.0
                    var infoWidth: Dp = if (maxWidth*4/5 < 550.dp) maxWidth*4/5 else 550.dp
                    Box(
                        modifier = Modifier.width(infoWidth).height((infoWidth.value*infoFrameRate).dp)
                    ){
                        Image(painter = painterResource(contextD.imageLoca[0]),"",modifier = Modifier.fillMaxHeight(), contentScale = ContentScale.FillHeight)
                        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("關於NaNa", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp, lineHeight = 48.sp)
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(contextD.info, modifier = Modifier.width(infoWidth-30.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(40.dp))
                    Text("聯絡我們", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)

                    Spacer(modifier = Modifier.height(40.dp))
                    Row (
                        modifier = Modifier.height(150.dp)
                    ) {
                        Spacer(modifier = Modifier.width(20.dp))
                        Image(painter = painterResource(contextD.imageLoca[2]),"", modifier = Modifier.fillMaxHeight(), contentScale = ContentScale.FillHeight)
                        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically){
                            Text(contextD.email, modifier = Modifier)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    Row (
                        modifier = Modifier.height(150.dp)
                    ) {
                        Spacer(modifier = Modifier.width(20.dp))
                        Image(painter = painterResource(contextD.imageLoca[3]),"", modifier = Modifier.fillMaxHeight(), contentScale = ContentScale.FillHeight)
                        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically){
                            Text(contextD.tel)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

            }
        }

    }


    }
@Composable
fun textTitle(titleName: List<Pair<String, Dp>>) {
    var presentWidth : Dp = titleName.reduce { acc, pair -> Pair(acc.first,acc.second+pair.second) }.second
    var HH = 100.dp
    Box(
        modifier = Modifier.fillMaxWidth().height(HH),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.height(HH).width(presentWidth)
        ) {
            for (i in titleName) {
                Text(
                    i.first,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    modifier = Modifier.width(i.second)
                )

            }
        }
        Divider(
            modifier = Modifier.offset(y = 15.dp).width(presentWidth - 20.dp),
            color = Color.Black,
            thickness = 2.dp
        )
    }


}
