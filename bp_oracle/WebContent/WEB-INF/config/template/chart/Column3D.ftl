<?xml version="1.0" encoding="GBK"?>
  <chart exportFileName="${((exportFileName)!' ')?html}" exportEnabled="1" exportAction="download" exportAtClient="0" exportHandler="${((ctx)!' ')?html}/FusionchartExporter.do" caption="${((caption)!' ')?html}" subcaption="${((unit)!' ')?html}" xAxisName="${((xAxisName)!' ')?html}" decimals="0" showBorder="${((showBorder)!'1')?html}" yAxisName="" numberPrefix="" baseFontColor="666666" showValues="0" formatNumberScale="${((formatNumberScale)!' ')?html}" baseFont="${((baseFont)!' ')?html}" BaseFontSize ="12">
    <#list baseChartList as cur>
      <set value="${((cur.value)!' ')?html}" label="${((cur.label)!' ')?html}" />
    </#list>
   </chart>