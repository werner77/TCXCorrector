<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        version="1.0"
>
    <!--<xsl:output indent="yes"/>-->
    <xsl:strip-space elements="*"/>

    <xsl:template match="HeartRateBpm">
        <xsl:if test="Value[text()!='0']">
            <xsl:copy>
                <xsl:apply-templates select="node()|@*"/>
            </xsl:copy>
        </xsl:if>
    </xsl:template>

    <xsl:template match="Trackpoint">
        <xsl:copy>
            <xsl:apply-templates select="Time"/>
            <xsl:apply-templates select="Position"/>
            <xsl:apply-templates select="AltitudeMeters"/>
            <xsl:apply-templates select="DistanceMeters"/>
            <xsl:apply-templates select="HeartRateBpm"/>
            <xsl:apply-templates select="Cadence"/>
            <xsl:apply-templates select="SensorState"/>
            <xsl:apply-templates select="Extensions"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="node()|@*">
        <xsl:copy>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:copy>
    </xsl:template>

</xsl:stylesheet>