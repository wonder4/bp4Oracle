<?xml version="1.0" encoding="GBK"?>
    <chart exportFileName="${((exportFileName)!' ')?html}" exportEnabled="1" exportAction="download" exportAtClient="0" exportHandler="${((ctx)!' ')?html}/FusionchartExporter.do" caption="${((caption)!' ')?html}" <#if (unit)??>subcaption="${((unit)!' ')?html}"</#if> numberPrefix="${((moneyUnit)!' ')?html}" lineThickness="1" decimals="2" showValues="0" formatNumberScale="0" anchorRadius="2"   divLineAlpha="20" divLineColor="CC3300" divLineIsDashed="1" showAlternateHGridColor="1" alternateHGridAlpha="5" alternateHGridColor="CC3300" shadowAlpha="40" labelStep="1" numvdivlines="5" chartRightMargin="35" bgColor="FFFFFF,CC3300" bgAngle="270" bgAlpha="10,10" baseFontColor="666666" baseFont="${((baseFont)!' ')?html}" BaseFontSize ="12">
      <categories >
	     <#list baseChartList as cur>
		    <category label="${((cur.category_label)!' ')?html}" />
	     </#list>
      </categories>
      <#list msLineChartList as cur>
        <dataset seriesName="${((cur.series_name)!' ')?html}" showValues="0">
          <#list cur.baseChartList as cur_base>
	    	<set value="${((cur_base.value)!' ')?html}" />
          </#list>
        </dataset>
     </#list>
      <styles>
        <definition>
          <style name="CaptionFont" type="font" size="12"/>
          <style name="SUBCAPTIONFont" type="font" size="12"/>
        </definition>
        <application>
          <apply toObject="CAPTION" styles="CaptionFont" />
          <apply toObject="SUBCAPTION" styles="SUBCAPTIONFont" />
        </application>
      </styles>
    </chart>
