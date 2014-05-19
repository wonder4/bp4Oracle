<?xml version="1.0" encoding="GBK"?>
<chart exportFileName="${((exportFileName)!' ')?html}" exportEnabled="1" exportAction="download" exportAtClient="0" exportHandler="${((ctx)!' ')?html}/FusionchartExporter.do" palette="2" caption="Monthly Unit Sales" xAxisName="Month" yAxisName="Units" showValues="0" decimals="0" formatNumberScale="0">
<set label="Jan" value="462" />
<set label="Feb" value="857" />
<set label="Mar" value="671" />
<set label="Apr" value="494" />
<set label="May" value="761" />
<set label="Jun" value="960" />
<vLine dashed="1" color="00AACC" thickness="2" dashLen="2" dashGap="6"/>
<set label="Jul" value="629" />
<set label="Aug" value="622" />
<set label="Sep" value="376" />
<set label="Oct" value="494" />
<set label="Nov" value="761" />
<set label="Dec" value="960" />
<styles>
        <definition>
            <style name="myAnim" type="animation" param="_yScale" start="0" duration="1"/> 
        </definition>
        <application>
            <apply toObject="VLINES" styles="myAnim" /> 
        </application>
    </styles>

</chart>