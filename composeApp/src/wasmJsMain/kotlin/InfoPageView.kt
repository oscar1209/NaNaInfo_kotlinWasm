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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.input.KeyboardType.Companion.Uri
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.w3c.dom.url.URL
import kotlin.math.pow
import kotlin.math.sign

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
    var imageScale: Float = 1f,
    var gapWeight: Triple<Float, Float, Dp>,
    var keyID: Int
)


@OptIn(ExperimentalResourceApi::class, ExperimentalComposeUiApi::class)
@Composable
fun InfoPageView(
    scrollState: LazyListState,
    contextManager: ContextManager,
    handler: (contextManager: ContextManager) -> Unit
) {

    var maxHeight = contextManager.maxHeight
    var maxWidth = contextManager.maxWidth
    var decide = contextManager.decide
    var state = contextManager.stateControl
    var scrollable by remember { mutableStateOf(true) }
    LazyColumn(
        userScrollEnabled = contextManager.decide == "phone",
        modifier = Modifier.fillMaxHeight().onPointerEvent(PointerEventType.Scroll){
            if (scrollable){
                if (contextManager.decide != "phone"){
                    val change = it.changes.first()
                    val delta = change.scrollDelta.y.toInt().sign

                    if (state.second == 0) {
                        if (delta == 1){
                            handler(contextManager.copy(stateControl = Triple(0,state.second + 1,false)))
                        }
                        /*
                        if (delta == -1){
                            handler(contextManager.copy(stateControl = Triple(0,10,false)))
                        }

                         */
                    }else if (state.second == 4){
                        if (delta == -1){
                            handler(contextManager.copy(stateControl = Triple(0,state.second - 1,false)))
                        }
                    }else if (state.second >= 7){

                    }else{
                        if (delta == -1){
                            handler(contextManager.copy(stateControl = Triple(0,state.second - 1,false)))
                        }
                        if (delta == 1){
                            handler(contextManager.copy(stateControl = Triple(0,state.second + 1,false)))
                        }
                    }
                    scrollable = false
                    GlobalScope.launch(Dispatchers.Main) {
                        delay(2600)
                        scrollable = true
                    }
                }

            }

        },
        state = scrollState
    ) {

        var heightLimits = contextManager.heightLimits
        var backgroundColor = Color(red = 245, green = 245, blue = 245)

        //最大/最小 高度/寬度 限制
        var showMaxWidth: Dp = 2000.dp
        var nowWidth: Dp = if (maxWidth > showMaxWidth) showMaxWidth else maxWidth
        //var widthHeightRate: Double = (1024.0/1440.0) //為最小高
        // var showMinHeight: Dp = heightLimits.dp
        var nowHeight: Dp = contextManager.nowViewHeight
        var textMaxWidth: Dp = 600.dp
        // var lineHeight = 40.sp
        var shiftText = 60.dp

        val forContextData = listOf(
            ContextData(
                title = "iOS 以及 android 原生語言進行堆疊式設計",
                textContext = "基於相同的堆疊邏輯，我們使用SwiftUI於iOS App, Koltlin Compose 於 andriod App。\n" +
                        "\n" +
                        "Swift 以及Koltin 皆是非常高效、安全的現代程式語言，我們可以讓兩個平台共享相同的堆疊邏輯，在加速開發流程的同時保證軟體的效率、易維護性。",
                textImage = Res.drawable.stack_design,
                backgroundImage = listOf<DrawableResource>(Res.drawable.rectangle_pink),
                keyID = 1
            ),

            ContextData(
                title = "優秀的圖形介面設計，令人安心的售後服務",
                textContext = "我們利用Figma設計美觀實用的UI介面，透過設計師親自與客戶進行溝通，以及對產品的反覆試驗，能確保提供最優質的使用者體驗。\n\n" +
                        "承諾為產品提供一個月售後服務，包括軟體錯誤的修正、使用經驗的優化，我們執著於對產品的雕琢，以確保能夠向客戶保証：\n" +
                        "「您的滿意是我們最在乎的事情。」\n",
                textImage = Res.drawable.Matlab,
                moreTextContext = listOf<String>(
                    "我們利用Figma設計美觀實用的UI介面，以及對產品的反覆試驗，能確保提供最優質的使用者體驗。",
                    "產品提供一個月售後服務，向客戶保証：\n" +
                            "「您的在乎是我們最在乎的事情。」\n"
                ),
                backgroundImage = listOf<DrawableResource>(Res.drawable.Blue_Top, Res.drawable.Blue_Bottom),
                keyID = 2
            ),
            ContextData(
                title = "Kotlin網頁及後端",
                textContext = "我們使用Kotlin各種工具，包括Kotlin/js、Kotlin/Wasm、Ktor來進行網頁以及伺服器的開發。\n" +
                        "\n" +
                        "透過Ktor設計的伺服器，將透過AWS的EC2服務進行託管，使用者不需要架設自己的伺服器主機。",
                textImage = Res.drawable.Koltin_Mark,
                backgroundImage = listOf<DrawableResource>(Res.drawable.pink_shape),
                keyID = 3
            ),
            ContextData(
                title = "作品：旅遊排程App",
                textContext = "．花蓮知名景點介紹與照片\n" +
                        "．解鎖情境文學\n" +
                        "．多景點路徑最佳化",
                textImage = Res.drawable.iphoneTitleView,
                moreTextContext = listOf<String>("旅遊排程App", "立即體驗"),
                backgroundImage = listOf<DrawableResource>(
                    Res.drawable.bluebutton,
                    Res.drawable.iphoneViewOne,
                    Res.drawable.iphoneViewTwo,
                    Res.drawable.QRcode,
                    Res.drawable.appleMark,

                    ),
                keyID = 4
            ),
        )
        item(0) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .height(contextManager.nowViewHeight)
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
                                            "iOS android \n" +
                                            "智慧解決方案        ",
                                    color = Color.White,
                                    fontSize = 24.sp,
                                    lineHeight = 36.sp,
                                    fontWeight = FontWeight.Light
                                )
                            } else {
                                var textSize = if (decide == "desktop") 28.sp else 24.sp
                                var lineH = if (decide == "desktop") 28.sp else 24.sp
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
                var componentWithForPair = (expressHeight.value * 0.6 + 60).dp
                //var componentWith = (maxWidth.value/4).dp
                // var photoLimit = 400.dp
                //var lineHeight = 35.sp
                //var desktopFontSize = 24.sp
                //var desktopLineHeight = 48.sp
                //var textHeight = (expressHeight - if ((componentWith.value * photo_rate).dp < photoLimit) (componentWith.value * photo_rate).dp else photoLimit)/2+30.dp
                val forTitleData = listOf(
                    titleData(
                        title = "iOS 以及 android 原生語言進行堆疊式設計",
                        textContext = "iOS 以及 android 原生語言進行堆疊式設計，共享相同的語言邏輯，兼具高效率、客製化、容易維護的特性",
                        textImage = Res.drawable.building_blocks_re_5ahy,
                        gapWeight = Triple(1f, 1f, 400.dp),// desktop, paid ,phone
                        keyID = 1
                    ),
                    titleData(
                        title = "優秀的圖形介面設計，令人安心的售後服務",
                        textContext = "優秀的圖形介面設計，令人安心的售後服務",
                        textImage = Res.drawable.progressive_app_m_9_ms,
                        gapWeight = Triple(1f, 0.8f, 400.dp),
                        imageScale = 1.1f,
                        keyID = 2
                    ),
                    titleData(
                        title = "Kotlin網頁及後端",
                        textContext = "Kotlin設計響應式網頁及後端，在AWS架設虛擬伺服器，提供一體、簡約、安全的選擇",
                        textImage = Res.drawable.server_cluster_jwwq,
                        gapWeight = Triple(1.5f, 0.01f, 170.dp),
                        keyID = 3
                    ),
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().height(expressHeight)
                        .background(Color.Black)
                ) {
                    if (decide == "desktop") {

                        Spacer(modifier = Modifier.widthIn(max = ((maxWidth - paid2desktop).value / 2).dp).weight(1f))


                    } else {
                        Spacer(modifier = androidx.compose.ui.Modifier.width((maxWidth.value / 15).dp))
                    }
                    if (decide == "desktop" || decide == "paid") {

                        for (titledD in forTitleData) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.width(componentWithForPair).height(expressHeight).clickable {

                                    handler(contextManager.copy(stateControl = Triple(0, titledD.keyID, state.third)))
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
                                    text = titledD.title,
                                    fontSize = 24.sp,
                                    lineHeight = 48.sp,
                                    color = Color.White,
                                    modifier = Modifier.align(Alignment.CenterHorizontally).widthIn(max = 250.dp),

                                    )
                                Spacer(modifier = Modifier.weight(titledD.gapWeight.first))
                            }

                            Spacer(modifier = Modifier.weight(titledD.gapWeight.second))
                        }


                    } else {
                        var info_underline by remember { mutableStateOf("") }
                        //  Spacer(modifier = androidx.compose.ui.Modifier.width((maxWidth.value / 15).dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(expressHeight)
                        ) {
                            Spacer(modifier = Modifier.height(50.dp))
                            for (titledD in forTitleData) {
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
                                        handler(
                                            contextManager.copy(
                                                stateControl = Triple(
                                                    0,
                                                    titledD.keyID,
                                                    state.third
                                                )
                                            )
                                        )
                                    }
                                    Spacer(Modifier.scale(1f))
                                }

                                Spacer(modifier = Modifier.height((expressHeight.value * 0.15).dp))
                            }
                            Spacer(modifier = Modifier.weight(1.0f))

                        }
                    }

                    if (decide == "desktop") {
                        Spacer(modifier = Modifier.widthIn(max = ((maxWidth - paid2desktop).value / 2).dp).weight(1f))
                    } else {
                        Spacer(modifier = androidx.compose.ui.Modifier.width((maxWidth.value / 15).dp))
                    }
                }

                Spacer(modifier = Modifier.weight(1.0f))
            }
            if (decide != "phone") {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.width(contextManager.maxWidth).height(contextManager.nowViewHeight * 4)
                ) {
                    // Layers1
                    Column {
                        for (i in 0..3) {
                            Spacer(
                                Modifier.width(contextManager.maxWidth).height(contextManager.nowViewHeight)
                                    .background(
                                        if (i == 1) Color.White else Color.Black
                                    )
                            )

                        }
                    }
                    // Layers2
                    //中間圖形元素的位置計算------------------------------------------------------------------------------------------------------------------
                    var back: Dp =
                        if (maxWidth > showMaxWidth) 0.dp else ((showMaxWidth - maxWidth).value * (contextManager.nowViewHeight.value) / 5500).dp
                    var imageHeight: Dp = (contextManager.nowViewHeight.value * (2.3 / 3)).dp - back
                    var push = if ((maxWidth - nowWidth) / 2 > 0.dp) (maxWidth - nowWidth) / 2 else 0.dp
                    var topAllPush =
                        if (push + (imageHeight.value * 0.5 / 3).dp - back > 0.dp) push + (imageHeight.value * 0.5 / 3).dp - back else 0.dp
                    var yry: Dp = (imageHeight.value * 1 / 5).dp
                    var p: Dp = maxWidth - topAllPush - yry
                    var xrx: Dp = (imageHeight.value * 1.5).dp
                    var bottomAllPush = p - xrx
                    var addTopAllPush = if (topAllPush > 40.dp) topAllPush else 40.dp

                    //ush + if (maxWidth > 1131.dp) maxWidth-1131.dp else 0.dp
                    var upContext: Dp = (contextManager.nowViewHeight - (imageHeight.value * 0.45).dp)
                    //------------------------------------------------------------------------------------------------------------------
                    Column(
                        modifier = Modifier.width(contextManager.maxWidth).height(contextManager.nowViewHeight * 4)
                    ) {
                        Spacer(Modifier.height(upContext))
                        //center image------------------------------------------------------------------------------------------------------------------
                        var contextD = forContextData[1]
                        var z: Dp = (maxWidth - topAllPush) * 1 / 3//與yry的距離
                        var textPush: Dp = p - z - textMaxWidth
                        var slope: Double = (445.0 / 3092.0)
                        var textTopPush: Dp =
                            ((((maxWidth - topAllPush) * 1 / 3).value + textMaxWidth.value / 2) * slope).dp
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.width(maxWidth)
                        )
                        {
                            //top element
                            Box(
                                contentAlignment = Alignment.TopEnd,
                                modifier = Modifier.width(maxWidth - topAllPush)//.background(Color.Green)
                            ) {
                                Image(
                                    painter = painterResource(forContextData[1].backgroundImage[0]),
                                    "",
                                    contentScale = ContentScale.FillHeight,
                                    modifier = Modifier.height(imageHeight),//.offset ( x = -push )
                                    alignment = Alignment.CenterEnd
                                )
                                Column(
                                    modifier = Modifier.width(maxWidth - topAllPush).height(imageHeight),

                                    ) {
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        contextD.moreTextContext[0],
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 24.sp,
                                        lineHeight = 48.sp,
                                        modifier = Modifier.width(textMaxWidth)
                                            .offset(x = textPush, y = -textTopPush - 10.dp).rotate(8f)
                                    )
                                }

                                /*
                                Column(
                                    modifier = Modifier.width(yry).height(imageHeight).background(Color(0x55A07E7E))
                                ) {
                                    Text("${textTopPush}"
                                        , color = Color.Red,
                                        fontSize = 24.sp)
                                    Text("${textPush}"
                                        , color = Color.Red,
                                        fontSize = 24.sp)

                                }
                                */

                            }
                            Spacer(modifier = Modifier.width(topAllPush))
                        }
                        //center

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.height((contextManager.nowViewHeight + back * 5) / 10).width(maxWidth)
                        ) {
                            Spacer(Modifier.weight(1f))
                            Text(
                                contextD.title,
                                fontSize = 32.sp,
                                //lineHeight = 48.sp,
                                fontWeight = FontWeight.ExtraBold,
                            )
                            Spacer(Modifier.width(addTopAllPush))
                        }


                        //Spacer(Modifier.height((contextManager.nowViewHeight+back*5)/10))
                        //bottom element
                        var bottomTextPush: Dp = (maxWidth - bottomAllPush) / 20

                        var bottomRate: Double = 103.0 / 801.0
                        var bottomUpTextPush: Dp = (imageHeight.value * bottomRate).dp
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.width(maxWidth)
                        )
                        {

                            Spacer(modifier = Modifier.width(bottomAllPush))
                            Box(
                                contentAlignment = Alignment.BottomStart,
                                modifier = Modifier.width(maxWidth - bottomAllPush)//.background(Color.Green)
                            ) {
                                Image(
                                    painter = painterResource(forContextData[1].backgroundImage[1]),
                                    "",
                                    contentScale = ContentScale.FillHeight,
                                    modifier = Modifier.height(imageHeight),
                                    alignment = Alignment.CenterStart//.offset ( x = push ),
                                )
                                Column(
                                    modifier = Modifier.width(maxWidth - bottomAllPush).height(imageHeight),

                                    ) {
                                    Text(
                                        contextD.moreTextContext[1],
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 24.sp,
                                        lineHeight = 48.sp,
                                        modifier = Modifier.width(textMaxWidth - 30.dp)
                                            .offset(x = bottomTextPush, y = bottomUpTextPush - 30.dp).rotate(-5f)
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                                /*
                                Column(
                                    modifier = Modifier.width(xrx).height(imageHeight).background(Color(0x55A07E7E))
                                ) {

                                }
                                 */
                            }
                        }
                        Spacer(Modifier.height(contextManager.nowViewHeight * 2))
                    }
                    // Layers3
                    Column(
                        modifier = Modifier.width(contextManager.maxWidth).height(contextManager.nowViewHeight * 4),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        //Spacer(Modifier.height((upContext.value*0.8/3).dp))
                        //Frist component----------------------------------------------------------------------------------------------------
                        Row(
                            Modifier.height(contextManager.nowViewHeight),
                            //.height((upContext.value*2.2/3).dp).offset(y = (upContext.value*0.5/3).dp)
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            var contextD = forContextData[0]
                            Spacer(modifier = Modifier.weight(1f))
                            if (nowWidth / 2 - 25.dp - shiftText < textMaxWidth + 30.dp) {
                                Spacer(modifier = Modifier.width(30.dp))
                            }
                            Column(
                                modifier = Modifier
                                    .width(nowWidth / 2 - 25.dp - shiftText)
                                    .height(nowHeight)//.background(color = Color.Blue)
                                , horizontalAlignment = Alignment.End
                            ) {
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = contextD.textContext,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = contextManager.wordColor,
                                    lineHeight = 48.sp,
                                    fontSize = 24.sp,
                                    modifier = Modifier.widthIn(max = textMaxWidth),

                                    )
                                Spacer(modifier = Modifier.weight(1f))
                            }
                            if (nowWidth / 2 - 25.dp - shiftText < textMaxWidth + 30.dp) {
                                Spacer(modifier = Modifier.width(20.dp))
                            } else {
                                Spacer(modifier = Modifier.width(50.dp))
                            }
                            Column(
                                modifier = Modifier
                                    .width(nowWidth / 2 - 25.dp + shiftText)
                                    .height(nowHeight)//.background(color = Color.Red)
                                , horizontalAlignment = Alignment.Start
                            ) {
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    contextD.title,
                                    color = Color.White,
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.ExtraBold

                                )
                                Spacer(modifier = Modifier.width(40.dp))
                                Image(
                                    painter = painterResource(contextD.textImage),
                                    contentDescription = "",
                                    modifier = Modifier.size(
                                        width = textMaxWidth,
                                        height = (textMaxWidth.value * 1.3 / 2).dp
                                    ),
                                    contentScale = ContentScale.FillWidth
                                )
                                Spacer(modifier = Modifier.weight(1f))
                            }
                            Spacer(modifier = Modifier.weight(1f))
                        }
                        //Frist component end------------------------------------------------------------------------------------------------------------------

                        //Second component---------------------------------------------------------------------------------------------------------------------
                        Row(
                            modifier = Modifier
                                .width(maxWidth)
                                .height(contextManager.nowViewHeight),
                        ) {
                            // var bottomSolpe: Double = 103.0/1224.0
                            var useCos: Double = 1224.0 / (1224.0.pow(2) + 103.0.pow(2)).pow(0.5)
                            var stopArea: Dp = bottomAllPush + (textMaxWidth.value * useCos).dp + 40.dp

                            Spacer(modifier = Modifier.width(stopArea))
                            Column(
                                modifier = Modifier
                                    .width(maxWidth - addTopAllPush - stopArea)
                                    .height(contextManager.nowViewHeight),//.background(Color.Green),
                                horizontalAlignment = Alignment.End
                            ) {
                                var contextD = forContextData[1]
                                Spacer(modifier = Modifier.height(upContext + imageHeight - contextManager.nowViewHeight + (contextManager.nowViewHeight + back * 5) / 8))
                                /*
                                Text(
                                    contextD.title,
                                    fontSize = 32.sp,
                                   //lineHeight = 48.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                )

                                 */
                                //Spacer(modifier = Modifier.height((contextManager.nowViewHeight+back*5)/21))
                                if (maxWidth - addTopAllPush - stopArea > 300.dp) {
                                    Image(
                                        painter = painterResource(contextD.textImage),
                                        contentDescription = "",
                                        modifier = Modifier.widthIn(max = textMaxWidth)
                                            .fillMaxWidth(),//size(width = textMaxWidth, height = (textMaxWidth.value*1.2/2).dp),
                                        contentScale = ContentScale.FillWidth
                                    )
                                }


                                Spacer(modifier = Modifier.weight(1f))
                            }

                            Spacer(modifier = Modifier.width(addTopAllPush))//.height(200.dp).background(Color.Green))
                        }


                        //Spacer(modifier = Modifier.height(contextManager.nowViewHeight))

                        //Second component end-----------------------------------------------------------------------------------------------------------------

                        //Third component----------------------------------------------------------------------------------------------------------------------
                        Row(
                            modifier = Modifier//.background(Color.Green)
                                .width(nowWidth)
                                .height(contextManager.nowViewHeight),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            var contextD = forContextData[2]
                            Spacer(modifier = Modifier.weight(1f))


                            //context2
                            Column(
                                modifier = Modifier
                                    .width(nowWidth / 2 - 25.dp + shiftText)
                                    .height(contextManager.nowViewHeight)//.background(color = Color.Red)
                                , horizontalAlignment = Alignment.End
                            ) {
                                Spacer(modifier = Modifier.weight(1f))
                                Image(
                                    painter = painterResource(contextD.textImage),
                                    contentDescription = "",
                                    modifier = Modifier.size(
                                        width = textMaxWidth,
                                        height = (textMaxWidth.value * 1.2 / 2).dp
                                    ),
                                    contentScale = ContentScale.FillWidth
                                )

                                Text(
                                    contextD.title,
                                    color = Color.White,
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                )
                                Spacer(modifier = Modifier.weight(1f))
                            }

                            if (nowWidth / 2 - 25.dp - shiftText < textMaxWidth + 30.dp) {
                                Spacer(modifier = Modifier.width(20.dp))
                            } else {
                                Spacer(modifier = Modifier.width(50.dp))
                            }

                            //context1

                            Column(
                                modifier = Modifier
                                    .width(nowWidth / 2 - 25.dp - shiftText)
                                    .height(nowHeight)//.background(color = Color.Blue)
                                , horizontalAlignment = Alignment.Start
                            ) {
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = contextD.textContext,
                                    fontWeight = FontWeight.ExtraBold,
                                    lineHeight = 48.sp,
                                    fontSize = 24.sp,
                                    modifier = Modifier.widthIn(max = textMaxWidth),
                                    color = contextManager.wordColor
                                )
                                Spacer(modifier = Modifier.weight(1f))
                            }
                            if (nowWidth / 2 - 25.dp - shiftText < textMaxWidth + 30.dp) {
                                Spacer(modifier = Modifier.width(30.dp))
                            }
                            Spacer(modifier = Modifier.weight(1f))
                        }
                        //  Spacer(Modifier.height((contextManager.nowViewHeight.value*1/3).dp))
                        //Third component end------------------------------------------------------------------------------------------------------------------
                        //fourth component------------------------------------------------------------------------------------------------------------------
                        Row(
                            Modifier.height(contextManager.nowViewHeight).width(nowWidth),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            var contextD = forContextData[3]
                            // var edge: Dp = if ((maxWidth - nowWidth)/2 > 0.dp) (maxWidth - nowWidth)/2 else 0.dp
                            Spacer(Modifier.widthIn(min = 200.dp).weight(0.7f))
                            Row(
                                Modifier.height(contextManager.nowViewHeight).width((nowWidth - 400.dp) / 2),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                var total_width = (nowWidth - 400.dp) / 2
                                Spacer(Modifier.width(20.dp))
                                Image(
                                    painter = painterResource(contextD.backgroundImage[1]), "",
                                    contentScale = ContentScale.FillWidth,
                                    modifier = Modifier.width((total_width - 80.dp) / 2)
                                )
                                Spacer(Modifier.width(20.dp))
                                Image(
                                    painter = painterResource(contextD.backgroundImage[2]), "",
                                    contentScale = ContentScale.FillWidth,
                                    modifier = Modifier.width((total_width - 80.dp) / 2)
                                )
                                Spacer(Modifier.width(40.dp))
                            }
                            //Spacer(Modifier.width(20.dp))
                            Column(
                                Modifier.height((nowWidth - 400.dp) / 2).width((nowWidth - 400.dp) / 2),
                                horizontalAlignment = Alignment.Start
                            ) {
                                var total_width = (nowWidth - 400.dp) / 2
                                Spacer(Modifier.weight(1f))
                                Text(
                                    contextD.textContext,
                                    color = contextManager.wordColor,
                                    fontSize = if (contextManager.decide == "desktop") 38.sp else 20.sp,
                                    lineHeight = if (contextManager.decide == "desktop") 54.sp else 37.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                                Spacer(Modifier.height(20.dp))
                                Row(
                                    verticalAlignment = Alignment.Top,
                                ) {
                                    Column(
                                        Modifier.height(contextManager.nowViewHeight).width(total_width / 2),
                                        horizontalAlignment = Alignment.Start
                                    ) {

                                        Image(
                                            painter = painterResource(contextD.backgroundImage[3]),
                                            "",
                                            Modifier.width((nowWidth - 40.dp) / 4),
                                            contentScale = ContentScale.FillWidth
                                        )
                                        if (contextManager.decide != "desktop") {
                                            Spacer(Modifier.height(10.dp))
                                        }

                                        Text(
                                            contextD.moreTextContext[0],
                                            color = Color.White,
                                            fontSize = if (contextManager.decide == "desktop") 36.sp else 22.sp,
                                            fontWeight = FontWeight.ExtraBold
                                        )
                                        Spacer(Modifier.height(10.dp))
                                        Image(
                                            painter = painterResource(contextD.backgroundImage[4]),
                                            "",
                                            Modifier.width(total_width / 4),
                                            contentScale = ContentScale.FillWidth
                                        )

                                    }
                                    Spacer(Modifier.width(20.dp))
                                    val uriHandler = LocalUriHandler.current
                                    Box(
                                        modifier = Modifier.clickable {
                                            uriHandler.openUri("https://apps.apple.com/tw/app/%E6%9A%A2%E9%81%8A%E8%8A%B1%E8%93%AE/id6455260456")
                                        },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(contextD.backgroundImage[0]),
                                            ""
                                        )
                                        Text(
                                            contextD.moreTextContext[1],
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Normal,
                                            modifier = Modifier.offset(y = -3.dp)
                                        )
                                    }

                                }
                                Spacer(Modifier.weight(1f))
                            }

                            Spacer(Modifier.widthIn(min = 200.dp).weight(0.3f))
                        }
                        // Spacer(Modifier.height(contextManager.nowViewHeight))
                        //fourth component end------------------------------------------------------------------------------------------------------------------
                    }


                }

            }
        }

        if (decide == "phone") {
            var changeNowViewHeight: Dp =
                if (maxWidth > contextManager.nowViewHeight * 9 / 10) maxWidth else contextManager.nowViewHeight
            items(items = forContextData,
                key = {
                    it.keyID
                }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(maxWidth).height(changeNowViewHeight).background(Color.Black)
                ) {
                    //1
                    Divider()
                    Spacer(modifier = Modifier.width(maxWidth).weight(1f).background(Color.Black))
                    Box(modifier = Modifier.width(maxWidth).height(maxWidth / 2)) {
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
                        color = Color.White,
                        modifier = Modifier.height(50.dp)
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = it.textContext,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 40.sp,
                        fontSize = 22.sp,
                        color = contextManager.wordColor,
                        modifier = Modifier.width(maxWidth - 40.dp),
                        textAlign = TextAlign.Justify
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    val uriHandler = LocalUriHandler.current
                    if (it.keyID == 4) {
                        Box(
                            modifier = Modifier.clickable {
                                uriHandler.openUri("https://apps.apple.com/tw/app/%E6%9A%A2%E9%81%8A%E8%8A%B1%E8%93%AE/id6455260456")
                            },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(it.backgroundImage[0]),
                                ""
                            )
                            Text(
                                it.moreTextContext[1],
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.offset(y = -3.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    //2

                }

            }


        }
       


    }

}