package io.github.ravindragv.nasapictures

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.github.ravindragv.nasapictures.model.ImageMetaData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assume.assumeNotNull
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class MoshiUnitTest {
    private lateinit var moshi: Moshi
    private lateinit var jsonAdapter:JsonAdapter<List<ImageMetaData>>
    private var imageMetaDataList: List<ImageMetaData>? = null

    @Before
    fun init() {
        // Get an instance to moshi
        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()
        val listImageMetaData = Types.newParameterizedType(List::class.java, ImageMetaData::class.java)

        // We expect a list of json objects of type ImageMetaData
        jsonAdapter = moshi.adapter(listImageMetaData)
        assumeNotNull(jsonAdapter)

        val inpStream = Scanner(javaClass.getResourceAsStream("/data.json"),
            "UTF-8")
            .useDelimiter("\\A").next()
        assumeNotNull(inpStream)

        imageMetaDataList = jsonAdapter.fromJson(inpStream.toString())
        assumeNotNull(imageMetaDataList)
    }

    @Test
    fun `Number of elements in the list`() {
        assertEquals(imageMetaDataList!!.size, 26)
    }

    @Test
    fun `Test the first entry in the list`() {
        assertEquals(imageMetaDataList!![0].copyright, "ESA/HubbleNASA")
        val date = SimpleDateFormat("yyyy-MM-dd").parse("2019-12-01")
        assertEquals(imageMetaDataList!![0].date, date)
        assertEquals(imageMetaDataList!![0].explanation, "Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring.    Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library")
        assertEquals(imageMetaDataList!![0].hdurl, "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg")
        assertEquals(imageMetaDataList!![0].media_type, "image")
        assertEquals(imageMetaDataList!![0].service_version, "v1")
        assertEquals(imageMetaDataList!![0].title, "Starburst Galaxy M94 from Hubble")
        assertEquals(imageMetaDataList!![0].url, "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg")
    }

    @Test
    fun `Check an item where copyright is missing`() {
        assertNull(imageMetaDataList!![8].copyright)
        val date = SimpleDateFormat("yyyy-MM-dd").parse("2019-12-11")
        assertEquals(imageMetaDataList!![8].date, date)
        assertEquals(imageMetaDataList!![8].explanation, "What has this supernova left behind?  As little as 2,000 years ago, light from a massive stellar explosion in the Large Magellanic Cloud (LMC) first reached planet Earth. The LMC is a close galactic neighbor of our Milky Way Galaxy and the rampaging explosion front is now seen moving out - destroying or displacing ambient gas clouds while leaving behind relatively dense knots of gas and dust.  What remains is one of the largest supernova remnants in the LMC: N63A.  Many of the surviving dense knots have been themselves compressed and may further contract to form new stars.  Some of the resulting stars may then explode in a supernova, continuing the cycle.  Featured here is a combined image of N63A in the X-ray from the Chandra Space Telescope and in visible light by Hubble.  The prominent knot of gas and dust on the upper right -- informally dubbed the Firefox -- is very bright in visible light, while the larger supernova remnant shines most brightly in X-rays.  N63A spans over 25 light years and lies about 150,000 light years away toward the southern constellation of Dorado.")
        assertEquals(imageMetaDataList!![8].hdurl, "https://apod.nasa.gov/apod/image/1912/N63A_HubbleChandraSchmidt_1019.jpg")
        assertEquals(imageMetaDataList!![8].media_type, "image")
        assertEquals(imageMetaDataList!![8].service_version, "v1")
        assertEquals(imageMetaDataList!![8].title, "N63A: Supernova Remnant in Visible and X-ray")
        assertEquals(imageMetaDataList!![8].url, "https://apod.nasa.gov/apod/image/1912/N63A_HubbleChandraSchmidt_960.jpg")
    }

    @Test
    fun `Check copyright name with accented letters`() {
        assertEquals(imageMetaDataList!![20].copyright, "Petr\nHorálek")
        val date = SimpleDateFormat("yyyy-MM-dd").parse("2019-12-26")
        assertEquals(imageMetaDataList!![20].date, date)
        assertEquals(imageMetaDataList!![20].explanation, "December's New Moon brought a solar eclipse to some for the holiday season. It also gave beautiful dark night skies to skygazers around the globe, like this moonless northern winter night. In the scene, bright stars of the Winter Hexagon along the Milky Way are rising. Cosy mountain cabins in the snowy foreground are near the village of Oravska Lesna, Slovakia. The shining celestial beacons marking the well-known asterism are Aldebaran, Capella, Pollux (and Castor), Procyon, Rigel, and Sirius. This winter nightscape also reveals faint nebulae in Orion, and the lovely Pleiades star cluster. Slide your cursor over the image to trace the winter hexagon, or just follow this link.")
        assertEquals(imageMetaDataList!![20].hdurl, "https://apod.nasa.gov/apod/image/1912/Orava_Duskova_WinterHexagon_0.png")
        assertEquals(imageMetaDataList!![20].media_type, "image")
        assertEquals(imageMetaDataList!![20].service_version, "v1")
        assertEquals(imageMetaDataList!![20].title, "The Northern Winter Hexagon")
        assertEquals(imageMetaDataList!![20].url, "https://apod.nasa.gov/apod/image/1912/Orava_Duskova_WinterHexagon_0.jpg")
    }
}