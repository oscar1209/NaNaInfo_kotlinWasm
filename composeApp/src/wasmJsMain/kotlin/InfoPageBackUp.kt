import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinproject.composeapp.generated.resources.*
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.background
import kotlinproject.composeapp.generated.resources.building_blocks_re_5ahy
import kotlinproject.composeapp.generated.resources.progressive_app_m_9_ms
import kotlinproject.composeapp.generated.resources.server_cluster_jwwq
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.DrawableResource
/*
@Immutable
data class ContextData @OptIn(ExperimentalResourceApi::class) constructor(
    var title: String,
    var textContext: String,
    var moreTextContext: List<String> = listOf<String>(),
    var textImage: DrawableResource,
    var backgroundImage: List<DrawableResource>,
    var keyID: Int
)

@Immutable
data class titleData @OptIn(ExperimentalResourceApi::class) constructor(
    var title: String,
    var textContext: String,
    var textImage: DrawableResource,
    var gapWeight: Triple<Float,Float,Dp>,
    var keyID: Int
)
*/




@OptIn(ExperimentalResourceApi::class)
@Composable
fun InfoPageBackup(
    scrollState: LazyListState,
    state: Triple<Int,Int,Boolean>,
    maxHeight: Dp,
    maxWidth: Dp,
    decide: String,
    handler: (State: Triple<Int, Int, Boolean>) -> Unit
) {


    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        state = scrollState
    ) {
        var heightLimits = 800
        var backgroundColor = Color(red = 245, green = 245, blue = 245)
        item(0) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .height(if (maxHeight.value < heightLimits) heightLimits.dp else maxHeight)
            ) {
                var imageHeight =
                    if (maxHeight.value < heightLimits) (heightLimits * 0.6).dp else (maxHeight.value * 0.60).dp
                AnimatedContent(
                    targetState = imageHeight,
                    transitionSpec = { fadeIn() togetherWith fadeOut() },
                    label = "selected state"
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth().height(imageHeight),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.background),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(maxWidth, imageHeight)

                        )
                        Row {

                            if (decide == "phone") {
                                Spacer(modifier = Modifier.width((maxWidth.value * 0.15).dp))
                                Text(
                                    modifier = Modifier.offset(x = -50.dp, y = imageHeight / 14),
                                    text = "迅捷            \n" +
                                            "   有效      \n" +
                                            "      \n" +
                                            "\n" +
                                            "iOS android Web \n" +
                                            "智慧解決方案        ",
                                    color = Color.White,
                                    fontSize = 24.sp,
                                    lineHeight = 36.sp,
                                    fontWeight = FontWeight.Light
                                )
                            } else {
                                var textSize = if ( decide == "desktop") 28.sp else 24.sp
                                var lineH = if ( decide == "desktop") 28.sp else 24.sp
                                Spacer(modifier = Modifier.width(160.dp))
                                Text(
                                    modifier = Modifier.offset(x = -50.dp, y = imageHeight / 14),
                                    text = "迅捷            \n" +
                                            "   有效      \n" +
                                            "      \n" +
                                            "\n" +
                                            "iOS android Web 智慧解決方案",
                                    color = Color.White,
                                    fontSize = textSize,
                                    lineHeight = lineH,
                                    fontWeight = FontWeight.Light
                                )
                            }

                            Spacer(modifier = Modifier.weight(1.0f))
                        }
                    }


                }
                var expressHeight =
                    (if (maxHeight.value < heightLimits) heightLimits.dp else maxHeight) - imageHeight
                var photo_rate = 0.6
                var componentWithForPair = (expressHeight.value * 0.6 + 20).dp
                var componentWith = (maxWidth.value/4).dp
                var photoLimit = 400.dp
                //var lineHeight = 35.sp
                var desktopFontSize = 18.sp
                var desktopLineHeight = 36.sp
                var textHeight = (expressHeight - if ((componentWith.value * photo_rate).dp < photoLimit) (componentWith.value * photo_rate).dp else photoLimit)/2+30.dp
                val forTitleData = listOf(
                    titleData(
                        title = "iOS 以及 android 原生語言進行堆疊式設計",
                        textContext = "iOS 以及 android 原生語言進行堆疊式設計，共享相同的語言邏輯，兼具高效率、客製化、容易維護的特性",
                        textImage = Res.drawable.building_blocks_re_5ahy,
                        gapWeight = Triple(0.8f,1f,400.dp) ,
                        keyID = 1
                    ),
                    titleData(
                        title = "Kotlin設計響應式網頁及後端",
                        textContext = "Kotlin設計響應式網頁及後端，在AWS架設虛擬伺服器，提供一體、簡約、安全的選擇",
                        textImage = Res.drawable.server_cluster_jwwq,
                        gapWeight = Triple(0.8f,1f,270.dp) ,
                        keyID = 2
                    ),
                    titleData(
                        title = "Matlab設計的智慧運算在客戶端直接運行",
                        textContext = "Matlab設計智慧運算，在android、iOS客戶端直接運行，充分保護個資",
                        textImage = Res.drawable.progressive_app_m_9_ms,
                        gapWeight = Triple(0.01f,0.01f, 380.dp),
                        keyID = 3
                    ),
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().height(expressHeight)
                        .background(brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(red = 0, green = 0, blue = 0),
                            Color(red = 50, green = 50, blue = 50)
                        )
                    ))
                ) {
                    if (decide == "desktop") {
                        if (maxWidth > 2000.dp){
                            Spacer(modifier = Modifier.widthIn(max =  ((maxWidth - 2000.dp).value/2).dp).weight(1f))
                        }else{
                            Spacer(modifier = Modifier.width(80.dp))
                        }

                    } else {
                       Spacer(modifier = androidx.compose.ui.Modifier.width((maxWidth.value /15).dp))
                    }
                    if (decide == "desktop" || decide == "paid" ){
                        if (decide == "desktop"){

                            for (titledD in forTitleData){
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.width(componentWith).height(expressHeight).clickable {
                                        handler(Triple(0,titledD.keyID,state.third))
                                    }
                                ) {
                                    Spacer(modifier = Modifier.weight(1.0f))
                                    Image(
                                        painter = painterResource(titledD.textImage),
                                        contentDescription = "",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.size(
                                            ((if ((componentWith.value * photo_rate).dp < photoLimit) (componentWith.value * photo_rate).dp else photoLimit).value*0.9).dp,
                                            ((if ((componentWith.value * photo_rate).dp < photoLimit) (componentWith.value * photo_rate).dp else photoLimit).value*0.9).dp
                                        ).offset(x = -10.dp)
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))

                                    Text(
                                        text = titledD.textContext,
                                        color = Color.White,
                                        fontSize = desktopFontSize,
                                        lineHeight = desktopLineHeight,
                                        modifier = Modifier.widthIn(max = 320.dp).align(alignment = Alignment.Top).offset(y = textHeight),
                                    )
                                    Spacer(modifier = Modifier.weight(1.0f))
                                }
                                Spacer(modifier = Modifier.weight(titledD.gapWeight.first))
                            }

                        }else{
                            for (titledD in forTitleData){
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.width(componentWithForPair).height(expressHeight).clickable {
                                        handler(Triple(0,titledD.keyID,state.third))
                                    }
                                ) {
                                    Spacer(modifier = Modifier.weight(1.0f))
                                    Image(
                                        painter = painterResource(titledD.textImage),
                                        contentDescription = "",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.size(
                                            (expressHeight.value * photo_rate).dp,
                                            (expressHeight.value * photo_rate).dp
                                        )
                                    )
                                    Text(
                                        text = titledD.textContext,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.weight(1.0f))
                                }

                                Spacer(modifier = Modifier.weight(titledD.gapWeight.second))
                            }

                        }

                    }else{
                        var info_underline by remember { mutableStateOf("") }
                      //  Spacer(modifier = androidx.compose.ui.Modifier.width((maxWidth.value / 15).dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(expressHeight)
                        ) {
                            Spacer(modifier = Modifier.height(50.dp))
                            for (titledD in forTitleData){
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        "． ",
                                        color = Color.White,
                                        modifier = Modifier.scale(1.25f)
                                    )
                                    PageButton(
                                        context = titledD.title,
                                        underLine = (info_underline == titledD.title),
                                        width = titledD.gapWeight.third,
                                        lineOffset = 0.5.dp,
                                        toLeft = true,
                                        textSize = 20.sp,
                                        handler = { info_underline = it }) {
                                        handler(Triple(0, titledD.keyID, state.third))
                                    }
                                    Spacer(Modifier.scale(1f))
                                }

                                Spacer(modifier = Modifier.height((expressHeight.value*0.15).dp))
                            }
                            Spacer(modifier = Modifier.weight(1.0f))

                        }
                    }

                    if (decide == "desktop") {
                        if (maxWidth > 2000.dp){
                            Spacer(modifier = Modifier.widthIn(max =  ((maxWidth - 2000.dp).value/2).dp).weight(1f))
                        }else{
                            Spacer(modifier = Modifier.width(80.dp))
                        }
                    } else {
                        Spacer(modifier = androidx.compose.ui.Modifier.width((maxWidth.value / 15).dp))
                    }
                }

                Spacer(modifier = Modifier.weight(1.0f))
            }

        }
        //最大/最小 高度/寬度 限制
        var showMaxWidth: Dp = 2000.dp
        var nowWidth: Dp = if(maxWidth > showMaxWidth) showMaxWidth else maxWidth
        //var widthHeightRate: Double = (1024.0/1440.0) //為最小高
        var showMinHeight: Dp = heightLimits.dp
        var nowHeight: Dp = if(maxHeight > showMinHeight) maxHeight else showMinHeight
        var textMaxWidth: Dp = 550.dp
        var lineHeight = 40.sp
        var shiftText = 60.dp

        val forContextData = listOf(
            ContextData(title = "iOS 以及 android 原生語言進行堆疊式設計",
                textContext = "基於相同的堆疊邏輯，我們使用SwiftUI於iOS App, Koltlin Compose 於 andriod App。\n" +
                        "\n" +
                        "Swift是由Apple於2014年發表的，專門用來撰寫如macOS/OS X、iOS、iPadOS等平台的軟體。\n" +
                        "\n" +
                        "Koltin是JetBrains於2016年發佈的一種改良自並能夠兼容Java的語言，並在2017年被Google指定為android開發的官方語言。\n" +
                        "\n" +
                        "Swift 以及Koltin 皆是非常高效、安全的現代程式語言，利用其中最新的UI工具SwiftUI以及Koltin Compose我們可以讓兩個平台共享相同的堆疊邏輯，在加速開發流程的同時保證軟體的執行效率，以及易維護性。",
                textImage = Res.drawable.stack_design,
                backgroundImage = listOf<DrawableResource>(Res.drawable.rectangle_pink),
                keyID = 1
            ),
            ContextData(title = "Kotlin設計響應式網頁及後端",
                textContext = "我們使用Kotlin的各種衍生工具，包括Ktor、Kotlin/js、Kotlin/Wasm來進行網頁前端以及後端伺服器的開發。\n" +
                        "\n" +
                        "Kotlin/js 將html 以及由facebook開發javascript著名框架React的語法融合到Kotlin，使得其可以在Kotlin的原生環境進行網頁開發，兼容 JavaScript 的任何依赖。\n" +
                        "\n" +
                        "Kotlin/Wasm更進一步地採用與設計android App相同的語法，來進行堆疊式設計，並編譯為新一代的WebAssembly二進位格式，比起傳統的javascript能夠更有效率的執行。\n" +
                        "\n" +
                        "透過Ktor設計的伺服器，將透過AWS的EC2服務進行託管，使用者不需要架設自己的伺服器主機。",
                textImage = Res.drawable.Koltin_Mark,
                backgroundImage = listOf<DrawableResource>(Res.drawable.pink_shape),
                keyID = 2
            ),
            ContextData(
                title = "Matlab設計的智慧運算在客戶端直接運行",
                textContext = "我們使用Matlabd開發前端智慧運算，利用其提供的工具轉換為C++，直接在客戶端的裝置運行。\n" +
                        "\n" +
                        "包括路徑組合最佳化、不正常心音的偵測等，透過Objective-C（iOS） 以及java（android）進行橋接，可以極有效率的在行動裝置中直接執行。\n" +
                        "\n" +
                        "相對於昂貴的雲端運算，選擇優化的小型神經網路能夠良好的適配於用戶的裝置，並且向用戶保證：\n" +
                        "「您的隱私是我們最在乎的事情。」\n",
                textImage = Res.drawable.Matlab,
                moreTextContext = listOf<String>(
                    "我們使用Matlabd開發前端智慧運算，利用其提供的工具轉換為C++，直接在客戶端的裝置運行。",
                    "包括路徑組合最佳化、不正常心音的偵測等，透過Objective-C（iOS） 以及java（android）進行橋接，可以極有效率的在行動裝置中直接執行。",
                    "相對於昂貴的雲端運算，選擇優化的小型神經網路能夠良好的適配於用戶的裝置，並且向用戶保證：\n" +
                            "「您的隱私是我們最在乎的事情。」\n"
                ),
                backgroundImage = listOf<DrawableResource>(),
                keyID = 3
            )
        )

        if (decide == "phone"){
            items(items = forContextData,
                key ={
                    it.keyID
                }
                ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(maxWidth).background(backgroundColor)
                ) {
                    //1
                    Divider()
                    Spacer(modifier = Modifier.width(maxWidth).height(40.dp).background(backgroundColor))
                    Box(modifier = Modifier.width(maxWidth).height(maxWidth/2)){
                        Image(
                            painter = painterResource(it.textImage),
                            contentDescription = "",
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.width(maxWidth),
                        )
                    }

                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = it.title,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp,
                        lineHeight = 36.sp,
                        modifier = Modifier.height(50.dp)
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = it.textContext,
                        fontWeight = FontWeight.Normal,
                        lineHeight = lineHeight,
                        fontSize = 16.sp,
                        modifier = Modifier.width(maxWidth-40.dp)
                    )

                    Spacer(modifier = Modifier.height(40.dp))
                    //2

                }

            }
            item {
                Spacer(modifier = Modifier.width(maxWidth).height(maxHeight/3).background(backgroundColor))
            }

        }else{
            //1
            item(1) {
                var contextD = forContextData[0]
                Box(
                    modifier = Modifier
                        .width(maxWidth)
                        .height(if(maxHeight>nowHeight) maxHeight else nowHeight)//.background(Color.Green),
                        //.background(color = backgroundColor)
                        .background(backgroundColor)
                    ,contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .width(nowWidth)
                            .height(nowHeight),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        if (nowWidth/2-25.dp-shiftText < textMaxWidth+30.dp){
                            Spacer(modifier = Modifier.width(30.dp))
                        }
                        Column(
                            modifier = Modifier
                                .width(nowWidth/2-25.dp-shiftText)
                                .height(nowHeight)//.background(color = Color.Blue)
                            ,horizontalAlignment = Alignment.End
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = contextD.textContext,
                                fontWeight = FontWeight.Normal,
                                lineHeight = lineHeight,
                                fontSize = 20.sp,
                                modifier = Modifier.widthIn(max = textMaxWidth),

                            )
                            Spacer(modifier = Modifier.weight(1f))
                        }
                        if (nowWidth/2-25.dp-shiftText < textMaxWidth+30.dp) {
                            Spacer(modifier = Modifier.width(20.dp))
                        }else{
                            Spacer(modifier = Modifier.width(50.dp))
                        }
                        Column(
                            modifier = Modifier
                                .width(nowWidth/2-25.dp+shiftText)
                                .height(nowHeight)//.background(color = Color.Red)
                            ,horizontalAlignment = Alignment.Start
                        )  {
                            Spacer(modifier = Modifier.weight(1f))
                            Image(
                                painter = painterResource(contextD.backgroundImage[0]),
                                contentDescription = "",
                                modifier = Modifier.widthIn(max = textMaxWidth).size(width = ((nowWidth/2-25.dp).value*2/3).dp, height = nowHeight),
                                contentScale = ContentScale.FillWidth
                            )
                            Spacer(modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    Row(
                        modifier = Modifier
                            .width(nowWidth)
                            .height(nowHeight),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Spacer(modifier = Modifier.width(nowWidth/2))
                        Column(
                            modifier = Modifier
                                .width(nowWidth/2)
                                .height(nowHeight)//.background(color = Color.Red)
                            ,horizontalAlignment = Alignment.Start
                        )  {
                            Spacer(modifier = Modifier.height(if(nowHeight/2-50.dp <maxHeight/2-50.dp) maxHeight/2-50.dp else nowHeight/2-50.dp))
                            Text(
                                text = contextD.title,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 32.sp,
                                lineHeight = 48.sp,
                                modifier = Modifier.height(100.dp)
                            )
                            if (decide == "paid"){
                                Spacer(modifier = Modifier.height(50.dp))
                            }

                            Image(
                                painter = painterResource(contextD.textImage),
                                contentDescription = "",
                                modifier = Modifier.widthIn(max = textMaxWidth*2).size(width = ((nowWidth/2-25.dp).value*4/5).dp, height = ((nowWidth/2-25.dp).value*4/9).dp),
                                contentScale = ContentScale.FillWidth
                            )
                            //  Spacer(modifier = Modifier.widthIn(min = 0.dp).height(maxHeight-nowHeight))
                            Spacer(modifier = Modifier.weight(1f))
                        }

                    }
                }
                Spacer(Modifier.height(40.dp).width(maxWidth).background(backgroundColor))
                Divider()
            }
            item(2) {
                var contextD = forContextData[1]
                Box(
                    modifier = Modifier
                        .width(maxWidth)
                        .height(if(maxHeight>nowHeight) maxHeight else nowHeight)//.background(Color.Green),
                        .background(color = backgroundColor)
                    ,contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .width(nowWidth)
                            .height(nowHeight),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.weight(1f))


                        //context2
                        Column(
                            modifier = Modifier
                                .width(nowWidth/2-25.dp+shiftText)
                                .height(nowHeight)//.background(color = Color.Red)
                            ,horizontalAlignment = Alignment.End
                        )  {
                            Spacer(modifier = Modifier.weight(1f))
                            Image(
                                painter = painterResource(contextD.backgroundImage[0]),
                                contentDescription = "",
                                modifier = Modifier.widthIn(max = textMaxWidth).size(width = ((nowWidth/2-25.dp).value*2/3).dp, height = nowHeight),
                                contentScale = ContentScale.FillWidth
                            )
                            Spacer(modifier = Modifier.weight(1f))
                        }

                        if (nowWidth/2-25.dp-shiftText < textMaxWidth+30.dp) {
                            Spacer(modifier = Modifier.width(20.dp))
                        }else{
                            Spacer(modifier = Modifier.width(50.dp))
                        }

                        //context1

                        Column(
                            modifier = Modifier
                                .width(nowWidth/2-25.dp-shiftText)
                                .height(nowHeight)//.background(color = Color.Blue)
                            ,horizontalAlignment = Alignment.Start
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = contextD.textContext,
                                fontWeight = FontWeight.Normal,
                                lineHeight = lineHeight,
                                fontSize = 20.sp,
                                modifier = Modifier.widthIn(max = textMaxWidth)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                        }
                        if (nowWidth/2-25.dp-shiftText < textMaxWidth+30.dp){
                            Spacer(modifier = Modifier.width(30.dp))
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    Row(
                        modifier = Modifier
                            .width(nowWidth)
                            .height(nowHeight),
                        verticalAlignment = Alignment.CenterVertically
                    ){

                        Column(
                            modifier = Modifier
                                .width(nowWidth/2)
                                .height(nowHeight)//.background(color = Color.Red)
                            ,horizontalAlignment = Alignment.End
                        )  {
                            Spacer(modifier = Modifier.height(if(nowHeight/2-50.dp <maxHeight/2-50.dp) maxHeight/2-50.dp else nowHeight/2-50.dp))
                            Text(
                                text = contextD.title,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 32.sp,
                                lineHeight = 48.sp,
                                modifier = Modifier.height(100.dp)
                            )
                            if (decide == "paid"){
                                Spacer(modifier = Modifier.height(50.dp))
                            }

                            Image(
                                painter = painterResource(contextD.textImage),
                                contentDescription = "",
                                modifier = Modifier.widthIn(max = textMaxWidth*2).size(width = ((nowWidth/2-25.dp).value*4/5).dp, height = ((nowWidth/2-25.dp).value*4/9).dp),
                                contentScale = ContentScale.FillWidth
                            )
                            //  Spacer(modifier = Modifier.widthIn(min = 0.dp).height(maxHeight-nowHeight))
                            Spacer(modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.width(nowWidth/2))

                    }
                }
                Spacer(Modifier.height(40.dp).width(maxWidth).background(backgroundColor))
                Divider()
            }


            //2

        }
        //第一張---------------------------------------------------------------------------------
        //第一張---------------------------------------------------------------------------------





    }

}