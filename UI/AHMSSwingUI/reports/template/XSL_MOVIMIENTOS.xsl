<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" >
	<xsl:decimal-format name="decimal" decimal-separator="," grouping-separator="."/>						
	<xsl:template match="/">		
		<xsl:variable name="lontipdoc">7cm</xsl:variable>
		<xsl:variable name="VmargenBody">10cm</xsl:variable>
		<xsl:variable name="MedidaFondoH">1cm</xsl:variable>
		<xsl:variable name="MedidaFondoV">1cm</xsl:variable>
		<xsl:variable name="topBodyRHC">4.4cm</xsl:variable>
		
		<xsl:call-template name="raiz">
			<xsl:with-param name="margenBody"><xsl:value-of select="$VmargenBody"/></xsl:with-param>
			<xsl:with-param name="lonEI"><xsl:value-of select="$lontipdoc"/></xsl:with-param>
			<xsl:with-param name="MedidasH"><xsl:value-of select="$MedidaFondoH"/></xsl:with-param>
			<xsl:with-param name="MedidasV"><xsl:value-of select="$MedidaFondoV"/></xsl:with-param>
			<xsl:with-param name="TopRHC"><xsl:value-of select="$topBodyRHC"/></xsl:with-param>
		</xsl:call-template>					
	</xsl:template> 

	<xsl:template name="raiz">
		<xsl:param name="margenBody"/>
		<xsl:param name="lonEI"/>
		<xsl:param name="MedidasH"/>
		<xsl:param name="MedidasV"/>
		<xsl:param name="TopRHC"/>
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<!--FIRST PAGE-->
			    <fo:simple-page-master master-name="firstpage" page-height="27.94cm" page-width="21.59cm">
					<fo:region-body region-name="first_body" margin-top="{$TopRHC}" margin-bottom="3cm"  margin-left="0.7cm"    background-position="center" background-position-vertical="{$MedidasV}" background-position-horizontal="{$MedidasH}"  background-repeat="no-repeat"    />
					<fo:region-before region-name="first_header" display-align="after" extent="11cm" margin-left="0.7cm"  />
					<fo:region-after    region-name="first_footer" display-align="before" extent="15cm" precedence="true"  />
				</fo:simple-page-master>
				<!--REST PAGE-->	
				<fo:simple-page-master master-name="restpage" page-height="27.94cm" page-width="21.59cm">
					<fo:region-body region-name="first_body" margin-top="{$TopRHC}" margin-bottom="3cm" margin-left="0.7cm"  background-position="center"  background-position-vertical="{$MedidasV}" background-position-horizontal="{$MedidasH}" background-repeat="no-repeat"    />
					<fo:region-before region-name="first_header" display-align="after" extent="11cm"   />
					<fo:region-after region-name="first_footer" display-align="before" extent="15cm" precedence="true"  />
				</fo:simple-page-master>
				<!--LAST PAGE-->
				<fo:simple-page-master master-name="lastpage" page-height="27.94cm" page-width="21.59cm">
					<fo:region-body region-name="first_body" margin-top="{$TopRHC}" margin-bottom="3cm" margin-left="0.7cm"  background-position="center" background-position-vertical="{$MedidasV}" background-position-horizontal="{$MedidasH}"  background-repeat="no-repeat"  />
					<fo:region-before region-name="first_header" display-align="after" extent="11cm"    />
					<fo:region-after region-name="first_footer" display-align="before" extent="15cm" precedence="true"  />						
				</fo:simple-page-master>						
				<fo:page-sequence-master master-name="default-sequence">
					<fo:repeatable-page-master-alternatives>
						<fo:conditional-page-master-reference master-reference="lastpage" page-position="last" />
						<fo:conditional-page-master-reference master-reference="firstpage" page-position="first" />
						<fo:conditional-page-master-reference master-reference="restpage" page-position="rest" />
					</fo:repeatable-page-master-alternatives>
				</fo:page-sequence-master>					
			</fo:layout-master-set>
			<xsl:apply-templates select="/root"/>
		</fo:root>
	</xsl:template>

	<xsl:template match="/root">		
		<fo:page-sequence master-reference="default-sequence">

			<xsl:variable name="topNotas">4.25cm</xsl:variable>
			<xsl:variable name="topImagenRfc">1.7cm</xsl:variable>
			<xsl:variable name="topImagenRfcCargoCredito">4.75cm</xsl:variable>
			<xsl:variable name="topTotales">1cm</xsl:variable>
			<xsl:variable name="topTotalesCargoCredito">6.5cm</xsl:variable>
			<xsl:variable name="topDeboYPagare">1.7cm</xsl:variable>
			<xsl:variable name="topMontoEnPalabras">2.5cm</xsl:variable>
			<xsl:variable name="topMontoEnPalabrasCargoCredito">7.48cm</xsl:variable>
			<xsl:variable name="topPagoUnaExhibicion">0cm</xsl:variable>

			<!-- VARIABLES DE LA CABECERA DEL REPORTE -->

			<xsl:variable name="usrCode"     select="/root/header/@usrCode"></xsl:variable>
			<xsl:variable name="usrFullName" select="/root/header/@usrFullName"></xsl:variable>
			<xsl:variable name="shtIni"      select="/root/header/@shtIni"></xsl:variable>
			<xsl:variable name="shtEnd"      select="/root/header/@shtEnd"></xsl:variable>

			
			<xsl:variable name="heightBodyRHC">12.7cm</xsl:variable>
			<xsl:variable name="topBodyRHC">4.3cm</xsl:variable>
					
			<!-- INICIO RECIBO  -->				
			<!-- INICIA FIRST HEADER -->
			<fo:static-content flow-name="first_header">				
				
				
				<!-- LOGO DE LA EMPRESA -->
				<!--<fo:block-container left="1cm" top="1cm" position="absolute">
					<fo:block><fo:external-graphic src="url('file:C:/Users/1067297/Desktop/OUTPUT2/logo.gif')"  content-height="scale-to-fit" height="2cm"  content-width="2.5cm" scaling="non-uniform"/></fo:block>	
				</fo:block-container>-->
				
				<!--<fo:block-container left="1cm" width="1cm" height="1cm" top="0.7cm" position="absolute">
					<fo:block>
						<fo:external-graphic src="url('file:C:/Users/1067297/Desktop/OUTPUT2/logo.jpg')" top="1cm" left="1cm" width="1cm" height="1cm"/>						
					</fo:block>
				</fo:block-container> -->
				
				<fo:block-container left="15cm"  width="4cm" height="2cm" top="1.2cm" position="absolute" font-size="8pt" color="#000000">
					<fo:table table-layout="fixed" width="3cm" height="2cm">
						<fo:table-column column-width="1.5cm"/>
						<fo:table-column column-width="4.2cm"/>
						<fo:table-body>
							<fo:table-row height="0.3cm">
								<fo:table-cell number-columns-spanned="2" text-align="right" font-weight="bold" padding-top="2pt" border-width="0.02cm" display-align="center"><fo:block text-align="right" font-size="8pt">REPORTE DE MOVIMIENTOS DE CUARTOS</fo:block></fo:table-cell>
							</fo:table-row>
							<fo:table-row height="0.3cm">
								<fo:table-cell text-align="right" number-columns-spanned="2" font-weight="normal" padding-top="2pt" border-width="0.02cm" display-align="center"><fo:block text-align="right" font-size="6pt"><xsl:value-of select="/root/header/@fecRpt"/></fo:block></fo:table-cell>
							</fo:table-row>													
						</fo:table-body>
					</fo:table>
				</fo:block-container>	

				<!-- DATOS DE LA EMPRESA-->
				<fo:block-container left="5cm" width="12cm" height="2cm" top="1cm" position="absolute">
					<fo:block>
						<fo:table  table-layout="fixed" width="12cm" height="2cm">
							<fo:table-column column-width="12cm"/>
								<fo:table-body>
									<fo:table-row height="0.3cm">
										<fo:table-cell  text-align="left" font-weight="normal" padding-top="2pt" border-width="0.02cm" display-align="center">
											<fo:block  text-align="left" font-size="6pt">HOTELUCHO CAGUENGUE</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row height="0.3cm">
										<fo:table-cell  text-align="left" font-weight="normal" padding-top="2pt" border-width="0.02cm" display-align="center">
											<fo:block  text-align="left" font-size="6pt">FIERRO PARIENTE !!!! </fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row height="0.3cm">
										<fo:table-cell  text-align="left" font-weight="normal" padding-top="2pt" border-width="0.02cm" display-align="center">
											<fo:block  text-align="left" font-size="6pt">R.F.C.&#160;CACA666666CACA</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row height="0.3cm">
										<fo:table-cell  text-align="left" font-weight="normal" padding-top="2pt" border-width="0.02cm" display-align="center">
											<fo:block  text-align="left" font-size="6pt">SI NO SABE TRAPEAR NO ME SIRVE</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:block-container>


				<!-- DATOS DE EMPLEADO Tabla 1-->
				<fo:block-container left="0.7cm" width="20cm" height="1cm" top="3.1cm" position="absolute">
					<fo:block>
						<fo:table   table-layout="fixed" width="20cm" height="3cm">
						<fo:table-column column-width="10cm"/>
						<fo:table-column column-width="10cm"/>
							<fo:table-body>
								<fo:table-row  height="0.3cm">
									<fo:table-cell  background-color="Gainsboro"  text-align="left" border-width="0.02cm" border-left="solid" border-right="solid" border-top="solid" border-bottom="solid" font-weight="normal" padding-top="2pt" display-align="center"><fo:block font-size="6pt" text-align="center" font-weight="bold">Fecha Inicio</fo:block></fo:table-cell>
									<fo:table-cell  background-color="Gainsboro"  text-align="left" border-width="0.02cm" border-left="solid" border-right="solid" border-top="solid" border-bottom="solid" font-weight="normal" padding-top="2pt" display-align="center"><fo:block font-size="6pt" text-align="center" font-weight="bold">Fecha Fin</fo:block></fo:table-cell>									
								</fo:table-row>
								<fo:table-row  height="0.3cm">
									<fo:table-cell   text-align="left" border-width="0.02cm" border-left="solid" border-right="solid" border-top="solid" border-bottom="solid" font-weight="normal" padding-top="2pt" display-align="center"><fo:block font-size="6pt" text-align="center"><xsl:value-of select="/root/header/@fecIni"/></fo:block></fo:table-cell>
									<fo:table-cell   text-align="left" border-width="0.02cm" border-left="solid" border-right="solid" border-top="solid" border-bottom="solid" font-weight="normal" padding-top="2pt" display-align="center"><fo:block font-size="6pt" text-align="center"><xsl:value-of select="/root/header/@fecFin"/></fo:block></fo:table-cell>									
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:block-container>

				<!-- CABECERAS DEL CUERPO -->
				<fo:block-container left="0.7cm" width="20cm" height="{$heightBodyRHC}" top="4cm" position="absolute" >
					<fo:block>
						<fo:table  table-layout="fixed" width="20cm" height="0.6cm">
						<fo:table-column column-width="4cm"/>
						<fo:table-column column-width="4cm"/>
						<fo:table-column column-width="2cm"/>
						<fo:table-column column-width="4cm"/>
						<fo:table-column column-width="2cm"/>
						<fo:table-column column-width="4cm"/>
							<fo:table-body>
								<fo:table-row  height="0.3cm">
									<fo:table-cell background-color="Gainsboro"  text-align="left" border-width="0.02cm" border-left="solid" border-right="solid" border-bottom="solid" border-top="solid" font-weight="normal" padding-top="2pt" display-align="center"><fo:block font-size="6pt" text-align="center" font-weight="bold" start-indent="2pt">FECHA</fo:block></fo:table-cell>
									<fo:table-cell background-color="Gainsboro"  text-align="left" border-width="0.02cm" border-left="solid" border-right="solid" border-bottom="solid" border-top="solid" font-weight="normal" padding-top="2pt" display-align="center"><fo:block font-size="6pt" text-align="center" font-weight="bold" start-indent="2pt">CLIENTE</fo:block></fo:table-cell>
									<fo:table-cell background-color="Gainsboro"  text-align="left" border-width="0.02cm" border-left="solid" border-right="solid" border-bottom="solid" border-top="solid" font-weight="normal" padding-top="2pt" display-align="center"><fo:block font-size="6pt" text-align="center" font-weight="bold" start-indent="2pt">ANTERIOR</fo:block></fo:table-cell>
									<fo:table-cell background-color="Gainsboro"  text-align="left" border-width="0.02cm" border-left="solid" border-right="solid" border-bottom="solid" border-top="solid" font-weight="normal" padding-top="2pt" display-align="center"><fo:block font-size="6pt" text-align="center" font-weight="bold" start-indent="2pt">MOTIVO</fo:block></fo:table-cell>
									<fo:table-cell background-color="Gainsboro"  text-align="left" border-width="0.02cm" border-left="solid" border-right="solid" border-bottom="solid" border-top="solid" font-weight="normal" padding-top="2pt" display-align="center"><fo:block font-size="6pt" text-align="center" font-weight="bold" start-indent="2pt">NUEVO</fo:block></fo:table-cell>
									<fo:table-cell background-color="Gainsboro"  text-align="left" border-width="0.02cm" border-left="solid" border-right="solid" border-bottom="solid" border-top="solid" font-weight="normal" padding-top="2pt" display-align="center"><fo:block font-size="6pt" text-align="center" font-weight="bold" start-indent="2pt">AUTORIZO</fo:block></fo:table-cell>
								</fo:table-row>								
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:block-container>	
				
			</fo:static-content>
					
			<!-- INICIA FIRST FOOTER -->
			<fo:static-content flow-name="first_footer">
				<!-- No Pagina -->
				<fo:block-container absolute-position="absolute" width="21cm" height="0.5cm" top="14cm" position="absolute" font-size="7pt" color="#000000">
					<fo:table table-layout="fixed">
						<fo:table-column column-width="21cm"/>
						<fo:table-body>
							<fo:table-row height="0.35cm">
								<fo:table-cell text-align="right" display-align="center" start-indent="2pt">
									<fo:block font-weight="bold">
										Página <fo:page-number/> de <fo:page-number-citation ref-id="last-page"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:block-container>				
			</fo:static-content>
					
			<!-- INICIA FIRST BODY -->
			<fo:flow flow-name="first_body" border="solid" >
				<fo:block>
					<fo:table   table-layout="fixed" width="20cm" height="3cm" >
						<fo:table-column column-width="4cm"/>
						<fo:table-column column-width="4cm"/>
						<fo:table-column column-width="2cm"/>
						<fo:table-column column-width="4cm"/>
						<fo:table-column column-width="2cm"/>
						<fo:table-column column-width="4cm"/>
						<fo:table-body>
							<xsl:for-each select="/root/Usuario">
								<xsl:variable name="pos_local_user" select="position()"/>
								<fo:table-row height="0.5cm">									
									<fo:table-cell number-columns-spanned="6" background-color="Gainsboro" text-align="left" border-width="0.02cm" border-left="solid" border-right="solid" border-bottom="solid"  border-top="solid" font-weight="normal" padding-top="2pt" display-align="center">
										<fo:block font-size="6pt" text-align="left" font-weight="bold" start-indent="2pt">
											&#160;&#160;&#160;<xsl:value-of select="/root/Usuario[$pos_local_user]/@usuReq"/>
										</fo:block>													
									</fo:table-cell>									
								</fo:table-row>
								<xsl:for-each select="/root/Usuario[$pos_local_user]/RoomMov">
									<xsl:variable name="pos_local_mov" select="position()"/>
									<fo:table-row height="0.5cm">											
											<fo:table-cell text-align="center" border-width="0.02cm" border-left="solid" border-right="solid" border-bottom="solid"  border-top="solid" font-weight="normal" padding-top="2pt" display-align="center">
												<fo:block font-size="6pt" text-align="center" font-weight="bold" start-indent="2pt">
												<xsl:value-of select="/root/Usuario[$pos_local_user]/RoomMov[pos_local_mov]/@fecMov"/>
												</fo:block>													
											</fo:table-cell>
											<fo:table-cell text-align="left" border-width="0.02cm" border-left="solid" border-right="solid" border-bottom="solid"  border-top="solid" font-weight="normal" padding-top="2pt" display-align="center">
												<fo:block font-size="6pt" text-align="left" font-weight="bold" start-indent="2pt">
												&#160;&#160;&#160;<xsl:value-of select="/root/Usuario[$pos_local_user]/RoomMov[pos_local_mov]/@cusName"/>
												</fo:block>													
											</fo:table-cell>
<fo:table-cell text-align="center" border-width="0.02cm" border-left="solid" border-right="solid" border-bottom="solid"  border-top="solid" font-weight="normal" padding-top="2pt" display-align="center">
												<fo:block font-size="6pt" text-align="center" font-weight="bold" start-indent="2pt">
												<xsl:value-of select="/root/Usuario[$pos_local_user]/RoomMov[pos_local_mov]/@rmsNumAnt"/>
												</fo:block>													
											</fo:table-cell>
											<fo:table-cell text-align="left" border-width="0.02cm" border-left="solid" border-right="solid" border-bottom="solid"  border-top="solid" font-weight="normal" padding-top="2pt" display-align="center">
												<fo:block font-size="6pt" text-align="left" font-weight="bold" start-indent="2pt">
												&#160;&#160;&#160;<xsl:value-of select="/root/Usuario[$pos_local_user]/RoomMov[pos_local_mov]/@movDesc"/>
												</fo:block>													
											</fo:table-cell>
<fo:table-cell text-align="center" border-width="0.02cm" border-left="solid" border-right="solid" border-bottom="solid"  border-top="solid" font-weight="normal" padding-top="2pt" display-align="center">
												<fo:block font-size="6pt" text-align="center" font-weight="bold" start-indent="2pt">
												<xsl:value-of select="/root/Usuario[$pos_local_user]/RoomMov[pos_local_mov]/@rmsNumNew"/>
												</fo:block>													
											</fo:table-cell>
											<fo:table-cell text-align="left" border-width="0.02cm" border-left="solid" border-right="solid" border-bottom="solid"  border-top="solid" font-weight="normal" padding-top="2pt" display-align="center">
												<fo:block font-size="6pt" text-align="left" font-weight="bold" start-indent="2pt">
												&#160;&#160;&#160;<xsl:value-of select="/root/Usuario[$pos_local_user]/RoomMov[pos_local_mov]/@usuAut"/>
												</fo:block>													
											</fo:table-cell>
										</fo:table-row>	
								</xsl:for-each>
							</xsl:for-each>										
						</fo:table-body>
					</fo:table>
				</fo:block>					
				<fo:block id="last-page" line-height="0"/>
			</fo:flow>								
		<!-- FIN RECIBO  -->
		</fo:page-sequence>
	</xsl:template>
</xsl:stylesheet>
