<?xml version="1.0" encoding="GBK"?>
  <chart exportFileName="${((exportFileName)!' ')?html}" exportEnabled="1" exportAction="download" exportAtClient="0" exportHandler="${((ctx)!' ')?html}/FusionchartExporter.do" caption="${((caption)!' ')?html}" subcaption="${((unit)!' ')?html}" palette="4" decimals="2" enableSmartLabels="1" enableRotation="0" bgColor="99CCFF,FFFFFF" bgAlpha="40,100" bgRatio="0,100" bgAngle="360" numberPrefix="${((numberPrefix)!' ')?html}" formatNumberScale="" showBorder="${((showBorder)!'1')?html}" baseFontColor="666666" baseFont="${((baseFont)!' ')?html}" BaseFontSize ="12">
    <#list baseChartList as cur>
      <set value="${((cur.value)!' ')?html}" label="${((cur.label)!' ')?html}" isSliced="${((cur.is_sliced)!' ')?html}"/>
    </#list>
  </chart>