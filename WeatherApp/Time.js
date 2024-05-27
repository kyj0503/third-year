export function getFormattedDate(date = new Date()) {
    const formattedDate = date.toISOString().slice(0, 10).replace(/-/g, '');
    console.log(`Formatted Date: ${formattedDate}`);
    return formattedDate;
  }
  
  export function getKoreanTime() {
    const now = new Date();
    const koreanTimeOffset = 9 * 60; // 9 hours in minutes
    now.setMinutes(now.getMinutes() + koreanTimeOffset);
    console.log(`Korean Time: ${now.toISOString()}`);
    return now;
  }
  
  export function getClosestBaseTime(baseTimes) {
    const now = getKoreanTime();
    const currentHour = now.getUTCHours();
  
    let closestBaseTime = baseTimes[0];
  
    for (let i = baseTimes.length - 1; i >= 0; i--) {
      const baseHour = parseInt(baseTimes[i].substring(0, 2), 10);
      if (currentHour >= baseHour) {
        closestBaseTime = baseTimes[i];
        break;
      }
    }
  
    console.log(`Closest Base Time: ${closestBaseTime}`);
  
    const baseDate = getFormattedDate(now);
  
    return {
      baseDate,
      baseTime: closestBaseTime
    };
  }
  