<?xml version="1.0" encoding="GBK"?>
    <chart exportFileName="${((exportFileName)!' ')?html}" exportEnabled="1" exportAction="download" exportAtClient="0" exportHandler="${((ctx)!' ')?html}/FusionchartExporter.do" caption="${((caption)!' ')?html}" subcaption="${((unit)!' ')?html}" numberPrefix="${((moneyUnit)!' ')?html}" decimals="2" 
    formatNumberScale="0" xAxisName="${((xAxisName)!' ')?html}" yAxisName="" yAxisMinValue="15000" numberPrefix="" showValues="0" alternateHGridColor="FCB541" alternateHGridAlpha="20" divLineColor="FCB541" divLineAlpha="50" canvasBorderColor="666666" baseFontColor="666666" lineColor="4F81BD" baseFont="${((baseFont)!' ')?html}" BaseFontSize ="12">
    <#list baseChartList as cur>
      <set value="${((cur.value)!' ')?html}" label="${((cur.label)!' ')?html}" />
    </#list>
      <styles>
        <definition>
          <style name="Anim1" type="animation" param="_xscale" start="0" duration="1" />
          <style name="Anim2" type="animation" param="_alpha" start="0" duration="0.6" />
          <style name="DataShadow" type="Shadow" alpha="40"/>
        </definition>
        <application>
          <apply toObject="DIVLINES" styles="Anim1" />
          <apply toObject="HGRID" styles="Anim2" />
          <apply toObject="DATALABELS" styles="DataShadow,Anim2" />
        </application>
      </styles>
    </chart>
