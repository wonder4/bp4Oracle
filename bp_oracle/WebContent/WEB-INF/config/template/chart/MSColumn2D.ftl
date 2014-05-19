<?xml version="1.0" encoding="GBK"?>
    <chart exportFileName="${((exportFileName)!' ')?html}" exportEnabled="1" exportAction="download" exportAtClient="0" exportHandler="${((ctx)!' ')?html}/FusionchartExporter.do" palette="2" caption="${((caption)!' ')?html}" subcaption="${((unit)!' ')?html}" shownames="1" showvalues="0" decimals="2" numberPrefix="${((moneyUnit)!' ')?html}" formatNumberScale="${((formatNumberScale)!' ')?html}" useRoundEdges="1" legendBorderAlpha="0" baseFontColor="666666" baseFont="${((baseFont)!' ')?html}" BaseFontSize ="12">
    <categories>
     <#list baseChartList as cur>
	    <category label="${((cur.category_label)!' ')?html}" />
     </#list>
    </categories>
    <#list mSColumn3DChartChartList as cur>
      <dataset seriesName="${((cur.series_name)!' ')?html}" showValues="0">
        <#list cur.baseChartList as cur_base>
	      <set value="${((cur_base.value)!' ')?html}" />
        </#list>
      </dataset>
    </#list>
    </chart>
