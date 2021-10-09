package io.github.ravindragv.nasapictures

import com.squareup.moshi.JsonDataException
import io.github.ravindragv.nasapictures.interfaces.ImageMetaDataJSONReader
import io.github.ravindragv.nasapictures.factory.ImageMetaDataJSONReaderFactory
import io.github.ravindragv.nasapictures.factory.JSONReader
import io.github.ravindragv.nasapictures.utilities.MoshiJSONReader
import org.junit.Assert
import org.junit.Assume
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class MoshiJSONReaderUnitTests {
    private lateinit var moshiJSONReader: ImageMetaDataJSONReader
    private lateinit var jsonInput: String

    @Before
    fun init() {
        jsonInput = Scanner(javaClass.getResourceAsStream("/data.json"),
            "UTF-8")
            .useDelimiter("\\A").next()
        Assume.assumeNotNull(jsonInput)

        moshiJSONReader = ImageMetaDataJSONReaderFactory.getReader(
            JSONReader.MOSHI,
            jsonInput)
        Assume.assumeNotNull(moshiJSONReader)
    }

    @Test
    fun `Check if ImageMetaDataJSONReaderFactory returns correct object`() {
        Assert.assertTrue(moshiJSONReader is MoshiJSONReader)
    }

    @Test
    fun `Check number of elements`() {
        val orderedList = moshiJSONReader.getObjectsOrderedByLatestDate()
        Assert.assertEquals(orderedList.size, 26)
    }

    @Test
    fun `Test the first entry in the orderedList`() {
        val orderedList = moshiJSONReader.getObjectsOrderedByLatestDate()

        Assert.assertEquals(orderedList[0].copyright, "Rui Liao")
        val date = SimpleDateFormat("yyyy-MM-dd").parse("2019-12-31")
        Assert.assertEquals(orderedList[0].date, date)
        Assert.assertEquals(
            orderedList[0].explanation,
            "The small, northern constellation Triangulum harbors this magnificent face-on spiral galaxy, M33. Its popular names include the Pinwheel Galaxy or just the Triangulum Galaxy. M33 is over 50,000 light-years in diameter, third largest in the Local Group of galaxies after the Andromeda Galaxy (M31), and our own Milky Way. About 3 million light-years from the Milky Way, M33 is itself thought to be a satellite of the Andromeda Galaxy and astronomers in these two galaxies would likely have spectacular views of each other's grand spiral star systems. As for the view from planet Earth, this sharp image shows off M33's blue star clusters and pinkish star forming regions along the galaxy's loosely wound spiral arms. In fact, the cavernous NGC 604 is the brightest star forming region, seen here at about the 7 o'clock position from the galaxy center. Like M31, M33's population of well-measured variable stars have helped make this nearby spiral a cosmic yardstick for establishing the distance scale of the Universe."
        )
        Assert.assertEquals(
            orderedList[0].hdurl,
            "https://apod.nasa.gov/apod/image/1912/M33-HaLRGB-RayLiao.jpg"
        )
        Assert.assertEquals(orderedList[0].media_type, "image")
        Assert.assertEquals(orderedList[0].service_version, "v1")
        Assert.assertEquals(orderedList[0].title, "M33: The Triangulum Galaxy")
        Assert.assertEquals(
            orderedList[0].url,
            "https://apod.nasa.gov/apod/image/1912/M33-HaLRGB-RayLiao1024.jpg"
        )
    }

    @Test
    fun `Test that the dates are ordered in descending order`() {
        val orderedList = moshiJSONReader.getObjectsOrderedByLatestDate()
        val descendingOrderList = orderedList.sortedByDescending { it.date }
        Assert.assertEquals(descendingOrderList, orderedList)
    }

    @Test
    fun `Fail for empty JSON String`() {
        Assert.assertThrows(IllegalArgumentException::class.java) {
            ImageMetaDataJSONReaderFactory.getReader(
                JSONReader.MOSHI,
                ""
            )
        }
    }

    @Test
    fun `Fail for invalid JSON String`() {
        Assert.assertThrows(JsonDataException::class.java) {
            ImageMetaDataJSONReaderFactory.getReader(
                JSONReader.MOSHI,
                "{\n" +
                        "        \"copyright\": \"Rui Liao\",\n" +
                        "}"
            )
        }
    }
}