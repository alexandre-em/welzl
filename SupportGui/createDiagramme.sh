#!/bin/bash

OUT_DATA_WELZL=./out/testWelzl.csv

# echo "Points Welzl" > ${OUT_DATA_WELZL}
# ls ./samples/*.points | while read f; do
# 	FILE=${f}
# 	OUTPUT=$(java -Dfile.encoding=UTF-8 -classpath ./out/production/SupportGui supportGUI.DiamRace b < $f)
# 	NB=$(echo $FILE | tr -dc '0-9')
#        echo "${NB} ${OUTPUT}" >> ${OUT_DATA_WELZL} #output: "Nombre_point temps_welzl"	
# done;
# echo "Fichier data: ${OUT_DATA_WELZL} cree"

OUT_DATA_NAIF=./out/testNaif.csv

# echo "Points Naif" > ${OUT_DATA_NAIF}
# ls ./samples/*.points | while read f; do
#         FILE=${f}
#         OUTPUT=$(java -Dfile.encoding=UTF-8 -classpath ./out/production/SupportGui supportGUI.DiamRace n < $f)
#         NB=$(echo $FILE | tr -dc '0-9')
#        echo "${NB} ${OUTPUT}" >> ${OUT_DATA_NAIF} #output: "Nombre_point temps_naif"     
# done;
# echo "Fichier data: ${OUT_DATA_NAIF} cree"

OUT_DATA_WELZL=./out/testPointsWelzl.csv

echo "Points Welzl" > ${OUT_DATA_WELZL}
ls ./input/welzl/*.points | while read f; do
	FILE=${f}
	OUTPUT=$(java -Dfile.encoding=UTF-8 -classpath ./out/production/SupportGui supportGUI.DiamRace b < $f)
	NB=$(echo $FILE | tr -dc '0-9')
       echo "${NB} ${OUTPUT}" >> ${OUT_DATA_WELZL} #output: "Nombre_point temps_welzl"	
done;
echo "Fichier data: ${OUT_DATA_WELZL} cree"

OUT_DATA_NAIF=./out/testPointsNaif.csv

# echo "Points Naif" > ${OUT_DATA_NAIF}
# ls ./input/*.points | while read f; do
#         FILE=${f}
#         OUTPUT=$(java -Dfile.encoding=UTF-8 -classpath ./out/production/SupportGui supportGUI.DiamRace n < $f)
#         NB=$(echo $FILE | tr -dc '0-9')
#        echo "${NB} ${OUTPUT}" >> ${OUT_DATA_NAIF} #output: "Nombre_point temps_naif"     
# done;
# echo "Fichier data: ${OUT_DATA_NAIF} cree"


OUT_DATA_RESULTS=./out/testResult.csv

#echo "Points Welzl Naif Ecart" > ${OUT_DATA_RESULTS}
#ls ./samples/*.points | while read f; do
#        FILE=${f}
#        OUTPUT=$(java -Dfile.encoding=UTF-8 -classpath ./out/production/SupportGui supportGUI.DiamRace n < $f)
#        NB=$(echo $FILE | tr -dc '0-9')
#       echo "${NB} ${OUTPUT}" >> ${OUT_DATA_RESULTS} #output: "Nombre_point rayonW rayonN ecart"     
#done;
#echo "Fichier data: ${OUT_DATA_RESULTS} cree"


cd ./out
gnuplot < ./createPlot.gnu && echo "Fichier graph: ./out/welzlPoint2.png cree" 
# gnuplot < ./testRes.gnu && echo "Fichier graph: ./out/testRes.png cree"
cd ..
