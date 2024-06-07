import androidx.compose.animation.*
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


import androidx.compose.ui.window.CanvasBasedWindow
import kotlinproject.composeapp.generated.resources.*
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.background
import kotlinproject.composeapp.generated.resources.building_blocks_re_5ahy
import kotlinx.coroutines.*
import org.jetbrains.compose.resources.*
import org.jetbrains.compose.resources.Font
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity


@OptIn(ExperimentalResourceApi::class, InternalResourceApi::class)
suspend fun loadCjkFont(): FontFamily {
    val regular = readResourceBytes("font/NotoSerifTC-Regular.otf")
    val bold = readResourceBytes("font/SmileySans-Oblique-Bold.ttf")
    val extraBold = readResourceBytes("font/DelaGothicOne-ExtraBold.ttf")
    var Han_Heavy = readResourceBytes("font/SourceHanSerif-Heavy.ttc")
    var Han_Regu = readResourceBytes("font/SourceHanSerif-Regular.ttc")
    //val regular = resource("font/NotoSansCJKsc-Regular.ttf").readBytes()
    // val bold = resource("font/NotoSansCJKsc-Bold.ttf").readBytes()
    // val italic = resource("font/NotoSansCJKsc-Italic.ttf").readBytes()

    return FontFamily(
        //  Font(identity = "NotoSerifTC-Regular", data = regular, weight = FontWeight.Normal),
        Font(identity = "SmileySans-Oblique-Bold", data = bold, weight = FontWeight.Bold),
        Font(identity = "DelaGothicOne-ExtraBold", data = extraBold, weight = FontWeight.Light),
        Font(identity = "SourceHanSerif-Regular", data = Han_Regu, weight = FontWeight.Normal),
        Font(identity = "SourceHanSerif-Regular", data = Han_Heavy, weight = FontWeight.ExtraBold)
    )
}


data class ContextManager(
    var maxHeight: Dp = 1.dp,
    var maxWidth: Dp = 1.dp,
    var decide: String = "",
    var heightLimits: Int = 800,
    var nowViewHeight: Dp = 1.dp,
    var wordColor: Color = Color(0xffFF1D7F),
    var stateControl: Triple<Int, Int, Boolean> = Triple(0, 0, false),

    //捲動頁面
)

val ContextManagerSaver = listSaver<ContextManager, Any>(
    save = { listOf(it.maxHeight, it.maxWidth, it.decide, it.stateControl) },//数组中保存的值和City中的属性是顺序对应的
    restore = {
        ContextManager(
            maxHeight = it[0] as Dp,
            maxWidth = it[1] as Dp,
            decide = it[2] as String,
            stateControl = it[3] as Triple<Int, Int, Boolean>,

            )
    }
)


var paid2desktop = 1550.dp //響應式設計
var phone2paid = 1150.dp //響應式設計

@OptIn(ExperimentalResourceApi::class, ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun App() {
    var contextManager by rememberSaveable(stateSaver = ContextManagerSaver) {
        mutableStateOf<ContextManager>(ContextManager())
    }
    var keepForNav: Boolean by remember { mutableStateOf(false) }
    var keepButton: Boolean by remember { mutableStateOf(false) }
    var typography by remember { mutableStateOf<androidx.compose.material.Typography?>(null) }
    LaunchedEffect(Unit) {
        val font = loadCjkFont()
        typography = androidx.compose.material.Typography(defaultFontFamily = font)
    }
    //介面由這裡開始
    MaterialTheme(typography = typography ?: MaterialTheme.typography) {
        //  var stateControl by remember { mutableStateOf(Triple(0,0,false)) }//頁面控制 第一項：捲軸位置 第二項：頁面控制 第三項：隱藏控制


        val coroutineScope = rememberCoroutineScope()//捲動頁面
        val listState: LazyListState = rememberLazyListState() //捲動頁面
        val listStateViewTwo: LazyListState = rememberLazyListState()

        var topＡnimation by remember { mutableStateOf(0f) }
        val transition = updateTransition(topＡnimation, label = "box state")
        val yy by transition.animateDp(
            label = "border width",
            transitionSpec = {
                if (this.initialState < -3f || this.initialState == this.targetState){
                    topＡnimation = 0f
                }
                tween()
            }
        ) { state ->
            if (state < -3f){
                if (state > -50f){
                    -state.dp
                }else{
                    50.dp
                }
            }else{
                0.dp
            }

        }




        //var test by remember { mutableStateOf(0f) }
        BoxWithConstraints(
            Modifier.fillMaxSize().onPointerEvent(PointerEventType.Scroll){
                if ((contextManager.stateControl.first == 0 && !listState.canScrollBackward) || (contextManager.stateControl.first == 1 && !listStateViewTwo.canScrollBackward)){
                    val change = it.changes.first()
                    val delta = change.scrollDelta.y
                    topＡnimation = delta
                    //test = delta
                }else{
                    topＡnimation = 0f
                }


            },
            propagateMinConstraints = true
        ) {
            val maxHeight = this.maxHeight //畫面高
            val maxWidth = this.maxWidth //畫面寬
            var decide = if (maxWidth > paid2desktop) "desktop" else if (maxWidth > phone2paid) "paid" else "phone"

            contextManager.maxWidth = this.maxWidth
            contextManager.maxHeight = this.maxHeight
            contextManager.decide =
                if (maxWidth > paid2desktop) "desktop" else if (maxWidth > phone2paid) "paid" else "phone"
            contextManager.nowViewHeight =
                if (maxHeight.value < contextManager.heightLimits) contextManager.heightLimits.dp else maxHeight
            var stateControl = contextManager.stateControl
            //var trytry by remember { mutableStateOf(0) }

//響應式設計
            //bar control
            var screenRate by remember { mutableStateOf(0f) }

            screenRate = 1/LocalDensity.current.density
            var nowPositionOut by remember { mutableStateOf(0f) }
            nowPositionOut = listStateViewTwo.firstVisibleItemIndex.toFloat() * contextManager.nowViewHeight.value * 2/screenRate + listStateViewTwo.firstVisibleItemScrollOffset
            var barHeight = maxHeight / (contextManager.nowViewHeight * 3)
            var rate = (1 - barHeight)*maxHeight.value/(2*contextManager.nowViewHeight.value)
            var dragControlY  by remember { mutableStateOf(0f) }
            var controlBarY  by remember { mutableStateOf(0f) }
            var onDrag by remember { mutableStateOf(false) }
           // var speed by remember { mutableStateOf(0) }

            controlBarY = if(onDrag) dragControlY else nowPositionOut*rate*screenRate
            val controlBarTransition = updateTransition(controlBarY, label = "controlBarTransition")

            val barY: Dp by controlBarTransition.animateDp(){


                it.dp

            }


            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                .offset(y = yy)
            ) {
                if (stateControl.first == 0) {
                    InfoPageView(scrollState = listState, contextManager = contextManager) { CManager ->
                        contextManager = CManager
                        if (contextManager.decide != "phone") {
                            if (contextManager.stateControl.second<7){
                                var nowPosition = listState.firstVisibleItemScrollOffset
                                var wantTo: Float = contextManager.stateControl.second * contextManager.nowViewHeight.value/screenRate
                                var shiftOffset = wantTo - nowPosition
                                coroutineScope.launch {
                                    listState.animateScrollBy(
                                        shiftOffset,
                                        animationSpec = tween(durationMillis = 2500)
                                    )
                                }
                            }

                        } else {
                            coroutineScope.launch {
                                listState.animateScrollToItem(contextManager.stateControl.second)
                            }
                        }
                        /*
                        if (contextManager.stateControl.second == 10){

                            TopMove()
                            contextManager.stateControl = Triple(contextManager.stateControl.first,0,false)
                        }

                         */


                    }
                }

                if (stateControl.first == 1) {
                    AboutMeView(scrollState = listStateViewTwo, contextManager = contextManager) { CManager ->
                        contextManager = CManager
                        coroutineScope.launch {
                            listStateViewTwo.animateScrollToItem(contextManager.stateControl.second)
                        }
                    }
                }
                var aa by remember { mutableStateOf(0) }
                var bb by remember { mutableStateOf(0f) }
                /*
                Text(
                    "${aa}\n"+
                            "${bb}",
                    color = Color.Red,
                    fontSize = 48.sp,
                    lineHeight = 72.sp
                )

                 */


                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                ) {
                    Nav(contextManager = contextManager,
                        Keep = keepForNav,
                        keepHandler = {keepForNav = it}
                    ) {
                        contextManager = it
                        if (it.stateControl.second < 7) {
                            if (contextManager.decide != "phone") {
                                var nowPosition = listState.firstVisibleItemScrollOffset
                                aa = nowPosition
                                var wantTo: Float =
                                    contextManager.stateControl.second * contextManager.nowViewHeight.value/screenRate
                                bb = wantTo
                                var shiftOffset = wantTo - nowPosition

                                coroutineScope.launch {
                                    if (contextManager.stateControl.first == 0) {
                                        listState.animateScrollBy(
                                            shiftOffset,
                                            animationSpec = tween(durationMillis = 2500)
                                        )
                                    } else {
                                        var p = contextManager.stateControl.second * contextManager.nowViewHeight.value*2*rate
                                     //   onDrag = true
                                        //speed = 500
                                     //   dragControlY = p
                                     //   GlobalScope.launch(Dispatchers.Main) {
                                     //   delay(100)
                                     //       speed = 0
                                    //        onDrag = false
                                      //  }
                                        //onDrag = false
                                        listStateViewTwo.animateScrollToItem(contextManager.stateControl.second)
                                    }

                                }
                            } else {

                                coroutineScope.launch {
                                    listState.animateScrollToItem(contextManager.stateControl.second)
                                }
                            }
                        }
                    }
                    if (stateControl.third && contextManager.decide == "phone") {
                        menu() {
                            //stateControl = it
                            var C = contextManager.copy(stateControl = it)
                            contextManager = C
                            if (it.first==0){
                                coroutineScope.launch {
                                    listState.animateScrollToItem(it.second)
                                }
                            }
                            if (it.first==1){
                                coroutineScope.launch {
                                    listStateViewTwo.animateScrollToItem(it.second)
                                }
                            }


                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth().offset(y = 0.1.dp)) {
                        Spacer(Modifier.weight(1f))
                        if ((keepButton || keepForNav) && contextManager.decide != "phone") {
                            menuForInfo(contextManager, handlerKeep = {
                                keepButton = it
                            }) { C ->

                                contextManager = C
                                if (C.stateControl.second < 7) {

                                    var nowPosition = listState.firstVisibleItemScrollOffset
                                    var wantTo: Float =
                                        contextManager.stateControl.second * contextManager.nowViewHeight.value/screenRate
                                    var shiftOffset = wantTo - nowPosition

                                    coroutineScope.launch {
                                        listState.animateScrollBy(
                                            shiftOffset,
                                            animationSpec = tween(durationMillis = 2500)
                                        )
                                    }

                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(200.dp))

                    }

                }
                if (contextManager.stateControl.first == 1 && decide != "phone"){
                    Column(
                        modifier = Modifier.width(maxWidth).height(maxHeight),
                        horizontalAlignment = Alignment.End
                    ) {


                        Column(
                            modifier = Modifier.width(15.dp).height(maxHeight)
                                .border(width = 0.5.dp, color = Color(red = 200, green = 200, blue = 200)).background(
                                Color(red = 245, green = 245, blue = 245)
                            ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier.width(10.dp).height(contextManager.nowViewHeight * barHeight)
                                    .offset(y = barY)
                                    .background(
                                        Color(red = 180, green = 180, blue = 180), shape = RoundedCornerShape(10.dp)
                                    ).pointerInput(Unit){
                                        detectDragGestures(
                                            onDragStart = {
                                                //Log.d("testscreen dragStart", "y = ${it.y.roundToInt()}")
                                            },
                                            onDrag = {
                                                    change, dragAmount ->
                                                onDrag = true
                                                if (controlBarY == nowPositionOut*rate*screenRate){
                                                    dragControlY = nowPositionOut*rate*screenRate
                                                }
                                                dragControlY = dragControlY + (change.position.y - change.previousPosition.y)*screenRate
                                                if (controlBarY<0f){
                                                    dragControlY = 0f
                                                }
                                                if (dragControlY > (maxHeight.value-contextManager.nowViewHeight.value * barHeight)){
                                                    dragControlY = (maxHeight.value-contextManager.nowViewHeight.value * barHeight)
                                                }


                                                var wantTo: Float = dragControlY/rate/screenRate
                                                var shiftOffset = wantTo - nowPositionOut
                                                coroutineScope.launch {
                                                    listStateViewTwo.animateScrollBy(
                                                        shiftOffset,
                                                        animationSpec = tween(durationMillis = 0)
                                                    )
                                                }
                                               // Log.d("testscreen dragging", "y = ${change.position.y.roundToInt()} | \uD835\uDEE5 ${change.positionChange().y.roundToInt()}")
                                            }, onDragEnd = {
                                                onDrag = false
                                            }
                                        )
                                    }
                            ) {

                            }
                        }


                    }
                }

                /*
                Column {
                    Spacer(modifier = Modifier.height(400.dp))

                    Text(
                        "${contextManager.stateControl.first}",
                        color = Color.Red,
                        fontSize = 48.sp
                    )
                    Text(
                        "${contextManager.stateControl.second}",
                        color = Color.Red,
                        fontSize = 48.sp
                    )
                    Text(
                        "${contextManager.stateControl.third}",
                        color = Color.Red,
                        fontSize = 48.sp
                    )
                }

                 */
                /*
                Column {
                    Text("third : ${contextManager.stateControl.third}", color = Color.Red, fontSize = 48.sp, lineHeight = 60.sp)
                    Text("keepButton : ${keepButton}", color = Color.Red, fontSize = 48.sp, lineHeight = 60.sp)
                }

                 */
                /*
                Column {
                    Text("screenRate: ${screenRate}", color = Color.Red, fontSize = 48.sp, lineHeight = 60.sp)
                    Text("nowPosition: ${aa}", color = Color.Red, fontSize = 48.sp, lineHeight = 60.sp)
                    Text("wantto: ${bb}", color = Color.Red, fontSize = 48.sp, lineHeight = 60.sp)
                    Text("stateControl: ${contextManager.stateControl.second}",color = Color.Red, fontSize = 48.sp, lineHeight = 60.sp)
                    Text("nowViewHeight: ${contextManager.nowViewHeight}", color = Color.Red, fontSize = 48.sp, lineHeight = 60.sp)
                }

                 */
/*
                Column {
                    Text("screenRate: ${screenRate}", color = Color.Red, fontSize = 48.sp, lineHeight = 60.sp)
                    Text("maxHeight: ${maxHeight}", color = Color.Red, fontSize = 48.sp, lineHeight = 60.sp)
                    Text("contextManager.nowViewHeight*2: ${contextManager.nowViewHeight*2}", color = Color.Red, fontSize = 48.sp, lineHeight = 60.sp)
                    Text("barY: ${barY}", color = Color.Red, fontSize = 48.sp,lineHeight = 60.sp)
                    Text("firstVisibleItemIndex: ${listStateViewTwo.firstVisibleItemIndex}", color = Color.Red, fontSize = 48.sp,lineHeight = 60.sp)
                    Text("${listStateViewTwo.firstVisibleItemScrollOffset}", color = Color.Red, fontSize = 48.sp,lineHeight = 60.sp)
                    Text("nowPosition: ${nowPositionOut}", color = Color.Red, fontSize = 48.sp,lineHeight = 60.sp)
                    Text("viewportEndOffset: ${listStateViewTwo.layoutInfo.viewportSize.height}", color = Color.Red, fontSize = 48.sp,lineHeight = 60.sp)
                    Text("dragControlY: ${dragControlY}", color = Color.Red, fontSize = 48.sp,lineHeight = 60.sp)
                }

 */



                /*
                Button(
                    modifier = Modifier.offset(y = 50.dp),
                    onClick = {
                        topＡnimation = true
                        GlobalScope.launch(Dispatchers.Main) {
                            delay(200)
                            topＡnimation = false
                        }

                    }) {
                    Text("test")
                }

                 */
            }


        }


        /*
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
        */

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun menuForInfo(contextManager: ContextManager, handlerKeep: (Boolean) -> Unit, handler: (ContextManager) -> Unit) {
    var info_underline by remember { mutableStateOf("") }
    var state = contextManager.stateControl
    Column(
        modifier = Modifier
            .width(100.dp)
            .height(150.dp)
            .onPointerEvent(eventType = PointerEventType.Enter, pass = PointerEventPass.Initial) {
                // info_underline = "介紹"
                handlerKeep(true)
            }.onPointerEvent(eventType = PointerEventType.Exit, pass = PointerEventPass.Final) {
                GlobalScope.launch(Dispatchers.Main) {
                    delay(500)
                    handlerKeep(false)

                }

            }.background(color = Color(red = 0f, green = 0f, blue = 0f, alpha = 0.85f), shape = RectangleShape)
    ) {

        Spacer(modifier = Modifier.weight(1.0f))
        Row {
            Text(
                "  ．",
                color = Color.White
            )
            PageButton(
                context = "介紹 1",
                width = 55.dp,
                textSize = 16.sp,
                underLine = (info_underline == "介紹 1"),
                handler = { info_underline = it }) {
                handler(contextManager.copy(stateControl = Triple(0, 1, false)))
                handlerKeep(false)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Text(
                "  ．",
                color = Color.White
            )
            PageButton(
                context = "介紹 2",
                width = 55.dp,
                textSize = 16.sp,
                underLine = (info_underline == "介紹 2"),
                handler = { info_underline = it }) {
                handler(contextManager.copy(stateControl = Triple(0, 2, false)), )
                handlerKeep(false)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Text(
                "  ．",
                color = Color.White
            )
            PageButton(
                context = "介紹 3",
                width = 55.dp,
                textSize = 16.sp,
                underLine = (info_underline == "介紹 3"),
                handler = { info_underline = it }) {
                handler(contextManager.copy(stateControl = Triple(0, 3, false)))
                handlerKeep(false)
            }
        }
        Spacer(modifier = Modifier.weight(1.0f))
    }
}

@Composable
fun menu(handler: (Triple<Int, Int, Boolean>) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color = Color(red = 0f, green = 0f, blue = 0f, alpha = 0.85f), shape = RectangleShape)

    ) {
        var info_underline by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(start = 30.dp)
        ) {

            Spacer(modifier = Modifier.weight(1.0f))
            Row {
                Text(
                    "．",
                    color = Color.White
                )
                PageButton(
                    context = "介紹",
                    underLine = (info_underline == "介紹"),
                    handler = { info_underline = it }) {
                    handler(Triple(0, 1, false))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Text(
                    "．",
                    color = Color.White
                )
                PageButton(
                    context = "作品",
                    underLine = (info_underline == "作品"),
                    handler = { info_underline = it }) {
                    handler(Triple(0, 4, false))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Text(
                    "．",
                    color = Color.White
                )
                PageButton(
                    context = "成員",
                    underLine = (info_underline == "成員"),
                    handler = { info_underline = it }) {
                    handler(Triple(1, 0, false))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Text(
                    "．",
                    color = Color.White
                )
                PageButton(
                    context = "聯絡我們",
                    underLine = (info_underline == "聯絡我們"),
                    width = 70.dp,
                    handler = { info_underline = it }) {
                    handler(Triple(1, 1, false))
                }
            }
            Spacer(modifier = Modifier.weight(1.0f))
        }

    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalResourceApi::class)
@Composable
fun Nav(contextManager: ContextManager,Keep: Boolean,animationController: () -> Unit = {}, keepHandler: (Boolean) -> Unit , handler: (ContextManager) -> Unit) {
    var decide = contextManager.decide
    var state = contextManager.stateControl
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(color = Color(red = 0f, green = 0f, blue = 0f, alpha = 0.7f), shape = RectangleShape)
            .onPointerEvent(eventType = PointerEventType.Enter){
                animationController()
            }

    ) {
        Spacer(modifier = Modifier.width(80.dp))
        Row(modifier = Modifier
            .width(300.dp)
            .offset(x = if (decide == "phone") -60.dp else 0.dp)
            .clickable {
                handler(contextManager.copy(stateControl = Triple(0, 0, false)))
            }) {
            Image(painter = painterResource(Res.drawable.my_mark), "")
            Text(
                "NaNa STUDIO",
                modifier = Modifier
                    .scale(1.5f)
                    .offset(y = 2.dp, x = 15.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.weight(1.0f))
        if (decide == "desktop" || decide == "paid") {
            Row() {
                var info_underline by remember { mutableStateOf("") }
                PageButton(context = "介紹", underLine = (info_underline == "介紹"), handler = {
                    info_underline = it

                    if (it == "") {
                        GlobalScope.launch(Dispatchers.Main) {
                            delay(500)
                            keepHandler(false)
                        //    handler(contextManager.copy(stateControl = Triple(state.first, 8, false)))
                        }

                    } else {
                        keepHandler(true)
                       // handler(contextManager.copy(stateControl = Triple(state.first, 8, true)))
                    }

                }) {
                    keepHandler(!Keep)
                    //handler(contextManager.copy(stateControl = Triple(state.first, 8, !state.third)))

                }
                Spacer(modifier = Modifier.width(20.dp))
                PageButton(
                    context = "作品",
                    underLine = (info_underline == "作品"),
                    handler = { info_underline = it }) {


                    handler(contextManager.copy(stateControl = Triple(0, 4, false)))
                }
                Spacer(modifier = Modifier.width(20.dp))
                PageButton(
                    context = "成員",
                    underLine = (info_underline == "成員"),
                    handler = { info_underline = it }) {
                    handler(contextManager.copy(stateControl = Triple(1, 0, false)))
                }
                Spacer(modifier = Modifier.width(20.dp))
                PageButton(
                    context = "聯絡我們",
                    underLine = (info_underline == "聯絡我們"),
                    width = 80.dp,
                    handler = { info_underline = it }) {
                    handler(contextManager.copy(stateControl = Triple(1, 1, false)))
                }
            }
        } else {
            Icon(
                vectorResource(Res.drawable.baseline_menu_24),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.clickable {

                    handler(contextManager.copy(stateControl = Triple(state.first, 9, !state.third)))
                }
            )
        }

        Spacer(modifier = Modifier.width(50.dp))
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PageButton(
    context: String,
    underLine: Boolean,
    width: Dp = 40.dp,
    lineOffset: Dp = 0.dp,
    textSize: TextUnit = 16.sp,
    toLeft: Boolean = false, // ture = 無線
    fontWeight: FontWeight = FontWeight.Normal,
    handler: (context: String) -> Unit,
    clickHandler: () -> Unit
) {
    Box(
        modifier = Modifier.width(width)
            .onPointerEvent(eventType = PointerEventType.Enter, pass = PointerEventPass.Initial) {
                // info_underline = "介紹"
                handler(context)
            }.onPointerEvent(eventType = PointerEventType.Exit, pass = PointerEventPass.Final) {
                handler("")
            }.clickable {
                clickHandler()
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                context,
                modifier = Modifier,
                color = Color.White,
                fontSize = textSize,
                fontWeight = fontWeight
            )
            if (toLeft) {
                Spacer(Modifier.weight(1f))
            }
        }

        if (underLine) {
            Divider(
                modifier = Modifier.offset(y = 20.dp + lineOffset),
                color = Color.White,
                thickness = 2.dp
            )

        }


    }

}

