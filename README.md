# TheHopby

Til að keyra forritið þarf að vera með PostgreSQL install-að á tölvunni.

Ef vandræði eru við keyrslu vegna gagnagrunnsins er gott að breyta línu 8 í application.properties í:
spring.jpa.hibernate.ddl-auto=create

og breyta því svo til baka í 
spring.jpa.hibernate.ddl-auto=update

ef á að keyra forritið aftur.

application.properties skránna er að finna í 
TheHopby -> src -> main -> resources -> application.properties



Hægt er að taka kommentið af línu 33 í HobbyController til að fá einhver gögn inn í verkefnið. Það startar fallinu start() og keyrist bara í fyrsta sinn sem farið er inná upphafssíðuna eftir hverja keyrslu á verkefninu. Ef stoppað er keyrsluna er gott að setja kommentið aftur á svo það verði ekki tvöfalt af hverju session-i.

HobbyConller skránna er að finna í 
TheGHopby -> src -> main -> java -> is -> hi -> hbv501g -> TheHopby -> Controllers -> HobbyController
