<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>  
<html>  
    <head>  
        <title>贡献信息</title>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>  
            //绘制饼图  
            function drawCircle(canvasId, data_arr, color_arr, text_arr)  
            {  
                var c = document.getElementById(canvasId);  
                var ctx = c.getContext("2d");  
  
                var radius = c.height / 2 - 20; //半径  
                var ox = radius + 20, oy = radius + 20; //圆心  
  
                var width = 30, height = 10; //图例宽和高  
                var posX = ox * 2 + 20, posY = 30;   //  
                var textX = posX + width + 5, textY = posY + 10;  
  
                var startAngle = 0; //起始弧度  
                var endAngle = 0;   //结束弧度  
                for (var i = 0; i < data_arr.length; i++)  
                {  
                    //绘制饼图  
                    endAngle = endAngle + data_arr[i] * Math.PI * 2; //结束弧度  
                    ctx.fillStyle = color_arr[i];  
                    ctx.beginPath();  
                    ctx.moveTo(ox, oy); //移动到到圆心  
                    ctx.arc(ox, oy, radius, startAngle, endAngle, false);  
                    ctx.closePath();  
                    ctx.fill();  
                    startAngle = endAngle; //设置起始弧度  
  
                    //绘制比例图及文字  
                    ctx.fillStyle = color_arr[i];  
                    ctx.fillRect(posX, posY + 20 * i, width, height);  
                    ctx.moveTo(posX, posY + 20 * i);  
                    ctx.font = 'bold 12px 微软雅黑';    //斜体 30像素 微软雅黑字体  
                    ctx.fillStyle = color_arr[i]; //"#000000";  
                    var percent = text_arr[i] + "：" + 100 * data_arr[i] + "%";  
                    ctx.fillText(percent, textX, textY + 20 * i);  
                }  
            }  
  
            function init() {  
                //绘制饼图  
                //比例数据和颜色  
                var data_arr = ${dataList}; 
                var color_arr = ${colorList};  
                var text_arr = ${textList};  
  
                drawCircle("canvas_circle", data_arr, color_arr, text_arr);  
            }  
  
            //页面加载时执行init()函数  
            window.onload = init;  
        </script>  
    </head>  
    <body>  
        <h3>贡献信息：</h3>  
        <p>  
            <canvas id="canvas_circle" width="500" height="300" style="border:2px solid #0026ff;" >  
                浏览器不支持canvas  
            </canvas>  
        </p>  
    </body>  
</html>  